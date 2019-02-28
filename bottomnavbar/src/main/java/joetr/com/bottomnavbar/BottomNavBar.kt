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

class BottomNavBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        post {
            children.forEach { child ->
                if(child is BottomNavBarIcon) {
                    child.container().setOnClickListener {
                        handleInput(child, child.parent as BottomNavBar)
                        child.performCustomClick()
                    }
                }
            }
        }
    }

    private fun handleInput(bottomNavBarIcon: BottomNavBarIcon, parent: BottomNavBar) {
        val transition = ChangeBounds()
        transition.interpolator = OvershootInterpolator(2.0f)
        transition.duration = 400

        TransitionManager.beginDelayedTransition(parent, transition)

        val set = ConstraintSet()

        set.clone(bottomNavBarIcon.container())
        bottomNavBarIcon.setVisibilityOfText(set, View.VISIBLE)
        bottomNavBarIcon.updateBackground()
        set.applyTo(bottomNavBarIcon.container())

        parent.children.forEach { view ->
            if(view.id != bottomNavBarIcon.id && view is BottomNavBarIcon) {
                set.clone(view.container())

                view.setVisibilityOfText(set, View.GONE)
                DrawableCompat.setTint(view.navBackground(), ContextCompat.getColor(context, android.R.color.transparent))

                set.applyTo(view.container())
            }
        }
    }
}