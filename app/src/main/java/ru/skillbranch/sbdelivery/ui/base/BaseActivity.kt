package ru.skillbranch.sbdelivery.ui.base

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.children
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_root.*
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.util.dpToIntPx


abstract class BaseActivity<T : BaseViewModel<out IViewModelState>> : AppCompatActivity() {
    protected abstract val viewModel: T
    protected abstract val layout: Int
    lateinit var navController: NavController

    val toolbarBuilder = ToolbarBuilder()

    //set listeners, tuning views
    abstract fun subscribeOnState(state: IViewModelState)

    abstract fun renderNotification(notification: Notification)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        setSupportActionBar(toolbar)
        viewModel.observeState(this) { subscribeOnState(it) }
        viewModel.observeNotifications(this) { renderNotification(it) }
        viewModel.observeNavigation(this) { subscribeOnNavigation(it) }

        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.saveState()
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewModel.restoreState()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun subscribeOnNavigation(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.To -> {
                navController.navigate(
                    command.destination,
                    command.args,
                    command.options,
                    command.extras
                )
            }

//            is NavigationCommand.FinishLogin -> {
//                navController.navigate(R.id.finish_login)
//                if (command.privateDestination != null) navController.navigate(command.privateDestination)
//            }
//
//            is NavigationCommand.StartLogin -> {
//                navController.navigate(
//                    R.id.start_login,
//                    bundleOf("private_destination" to (command.privateDestination ?: -1))
//                )
//            }
        }
    }
}

/***
 * Toolbar builder for customizing common toolbar for every screen.
 * You can setup title and subtitle, set image for logo with URL.
 * Also you can add items to the toolbar menu and customize them.
 */
class ToolbarBuilder {
    private var title: String? = null
    private var subtitle: String? = null
    private var logo: String? = null
    private var visibility: Boolean = true
    private val items: MutableList<MenuItemHolder> = mutableListOf()

    fun setTitle(title: String): ToolbarBuilder {
        this.title = title
        return this
    }

    fun setSubtitle(subtitle: String): ToolbarBuilder {
        this.subtitle = subtitle
        return this
    }

    fun setLogo(logo: String): ToolbarBuilder {
        this.logo = logo
        return this
    }

    fun setVisibility(isVisible: Boolean): ToolbarBuilder {
        this.visibility = isVisible
        return this
    }

    fun addMenuItem(item: MenuItemHolder): ToolbarBuilder {
        this.items.add(item)
        return this
    }

    fun getItems() = items

    fun invalidate(): ToolbarBuilder {
        this.title = null
        this.subtitle = null
        this.logo = null
        this.visibility = true
        this.items.clear()
        return this
    }

    fun prepare(prepareFn: (ToolbarBuilder.() -> Unit)?): ToolbarBuilder {
        prepareFn?.invoke(this)
        return this
    }

    fun build(context: FragmentActivity) {

        //expand appbar if it was hidden while scrolling on the previous screen
        context.appbar.setExpanded(true, true)

        with(context.toolbar) {
            if (this@ToolbarBuilder.title != null) title = this@ToolbarBuilder.title
            subtitle = this@ToolbarBuilder.subtitle

            //setup parameters for logo image view if logo != null
            if (this@ToolbarBuilder.logo != null) {
                val logoSize = context.dpToIntPx(40)
                val logoMargin = context.dpToIntPx(16)
                val logoPlaceholder =
                    AppCompatResources.getDrawable(context, R.drawable.logo_placeholder)

                logo = logoPlaceholder

                val logo = children.last() as? ImageView
                if (logo != null) {
                    logo.scaleType = ImageView.ScaleType.CENTER_CROP
                    (logo.layoutParams as? Toolbar.LayoutParams)?.let {
                        it.width = logoSize
                        it.height = logoSize
                        it.marginEnd = logoMargin
                        logo.layoutParams = it
                    }

                    Glide.with(context)
                        .load(this@ToolbarBuilder.logo)
                        .apply(RequestOptions.circleCropTransform())
                        .override(logoSize)
                        .into(logo)
                }
            } else {
                logo = null
            }
        }
    }
}

/***
 * This class describes menu item for toolbar.
 */
data class MenuItemHolder(
    val title: String,
    val menuId: Int,
    val icon: Int,
    val actionViewLayout: Int? = null,
    val clickListener: ((MenuItem) -> Unit)? = null
)