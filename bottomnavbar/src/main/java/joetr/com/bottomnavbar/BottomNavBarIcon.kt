package joetr.com.bottomnavbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import kotlinx.android.synthetic.main.nav_bar_icon.view.*

/**
 * Icon to be contained in [BottomNavBar]
 */
class BottomNavBarIcon @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var foregroundTint: Int = 0
    private var clickListener: View.OnClickListener? = null

    init {
        val inflatedView = LayoutInflater.from(context)
            .inflate(R.layout.nav_bar_icon, this, true)

        attrs?.let { unwrappedAttributes ->
            val typedArray = context.obtainStyledAttributes(unwrappedAttributes, R.styleable.BottomNavBarIcon, defStyleAttr, 0)
            val iconText = typedArray.getString(R.styleable.BottomNavBarIcon_navText)
            val icon = typedArray.getDrawable(R.styleable.BottomNavBarIcon_navIcon)
            val backgroundTint = typedArray.getColor(R.styleable.BottomNavBarIcon_navForegroundTint, 0)
            foregroundTint = typedArray.getColor(R.styleable.BottomNavBarIcon_navBackgroundTint, 0)
            val selected = typedArray.getBoolean(R.styleable.BottomNavBarIcon_selected, false)
            val showIconOnRight = typedArray.getBoolean(R.styleable.BottomNavBarIcon_showIconOnRight, false)

            if (showIconOnRight) {
                showIconOnRight(inflatedView.bottomNavBarContainer)
            }

            inflatedView.bottomNavBarText.setTextColor(backgroundTint)
            inflatedView.bottomNavBarIcon.setImageDrawable(icon)
            inflatedView.bottomNavBarText.text = iconText

            if(selected) {
                inflatedView.bottomNavBarText.visibility = View.VISIBLE
                DrawableCompat.setTint(inflatedView.bottomNavBarContainer.background, foregroundTint)
            } else {
                inflatedView.bottomNavBarText.visibility = View.GONE
                DrawableCompat.setTint(inflatedView.bottomNavBarContainer.background, ContextCompat.getColor(getContext(), android.R.color.transparent))
            }

            typedArray.recycle()
        }
    }

    private fun showIconOnRight(container: ConstraintLayout) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(container)

        // Set layout values for icon
        constraintSet.connect(R.id.bottomNavBarIcon, ConstraintSet.START, R.id.bottomNavBarText, ConstraintSet.END)
        constraintSet.connect(R.id.bottomNavBarIcon, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
        constraintSet.connect(R.id.bottomNavBarIcon, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        constraintSet.connect(R.id.bottomNavBarIcon, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)

        // Set layout values for text
        constraintSet.connect(R.id.bottomNavBarText, ConstraintSet.END, R.id.bottomNavBarIcon, ConstraintSet.START)
        constraintSet.connect(R.id.bottomNavBarText, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        constraintSet.connect(R.id.bottomNavBarText, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
        constraintSet.connect(R.id.bottomNavBarText, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)

        constraintSet.applyTo(container)
    }

    /**
     * Set visibility of only TextView
     *
     * @param set - ConstraintSet
     * @param visibility - Visibility Int
     */
    fun setVisibilityOfText(set: ConstraintSet, visibility: Int) {
        set.setVisibility(bottomNavBarText.id, visibility)
    }

    /**
     * Set background tint
     */
    fun setBackgroundTint() {
        DrawableCompat.setTint(container().background, foregroundTint)
    }

    /**
     * Register click listener.
     *
     * This is the mechanism for having a developer get notified of a click event
     */
    fun registerClickListener(clickListener: View.OnClickListener) {
        this.clickListener = clickListener
    }

    /**
     * If developer registered a click listener, call it.
     */
    fun performCustomClick() {
        clickListener?.onClick(container())
    }

    fun container() = bottomNavBarContainer!!
}

