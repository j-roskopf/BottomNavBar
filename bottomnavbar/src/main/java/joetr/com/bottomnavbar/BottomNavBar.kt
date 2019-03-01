package joetr.com.bottomnavbar

import android.content.Context
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.children

/**
 * Container for [BottomNavBarIcon]
 */
class BottomNavBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        //make sure all children are attached
        post {
            children.forEach { child ->
                if(child is BottomNavBarIcon) {
                    child.container().setOnClickListener {
                        handleClick(child, child.parent as BottomNavBar)

                        //perform custom click if developer registered custom click
                        child.performCustomClick()
                    }
                }
            }
        }
    }

    /**
     * Handles click for icon
     *
     * Responsible for animating the clicked one to be selected, as well as deselecting other tabs.
     *
     * @param bottomNavBarIcon - Icon that was clicked on
     * @param parent - Parent [BottomNavBar]
     */
    private fun handleClick(bottomNavBarIcon: BottomNavBarIcon, parent: BottomNavBar) {
        val transition = ChangeBounds()
        transition.interpolator = OvershootInterpolator(2.0f)
        transition.duration = 400

        TransitionManager.beginDelayedTransition(parent, transition)

        val set = ConstraintSet()

        selectEnabledIcon(set, bottomNavBarIcon)

        disableOtherIcons(parent, set, bottomNavBarIcon)
    }

    /**
     * Selects icon
     *
     * @param set - constraint set
     * @param bottomNavBarIcon - icon
     */
    private fun selectEnabledIcon(set: ConstraintSet, bottomNavBarIcon: BottomNavBarIcon) {
        set.clone(bottomNavBarIcon.container())
        bottomNavBarIcon.setVisibilityOfText(set, View.VISIBLE)
        bottomNavBarIcon.setBackgroundTint()
        set.applyTo(bottomNavBarIcon.container())
    }

    /**
     * Deselects other icons
     *
     * @param parent - [BottomNavBar] container
     * @param set - ConstraintSet
     */
    private fun disableOtherIcons(
        parent: BottomNavBar,
        set: ConstraintSet,
        bottomNavBarIcon: BottomNavBarIcon
    ) {
        parent.children.forEach { view ->
            if(view.id != bottomNavBarIcon.id && view is BottomNavBarIcon) {
                set.clone(view.container())

                view.setVisibilityOfText(set, View.GONE)
                DrawableCompat.setTint(view.container().background, ContextCompat.getColor(context, android.R.color.transparent))

                set.applyTo(view.container())
            }
        }
    }
}