package joetr.com.bottomnavbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
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

    private var foregroundTint : Int = 0
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

