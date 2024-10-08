package com.spe.latihanwidget.stackviewwidget

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.os.bundleOf
import com.spe.latihanwidget.R

internal class StackRemoteViewsFactory(private val mContext: Context) : RemoteViewsService.RemoteViewsFactory {

    private val mWidgetItems = ArrayList<Bitmap>()

    override fun onCreate() {

    }

    override fun onDataSetChanged() {
        mWidgetItems.add(BitmapFactory.decodeResource(mContext.resources, R.drawable.photo_1))
        mWidgetItems.add(BitmapFactory.decodeResource(mContext.resources, R.drawable.photo_2))
        mWidgetItems.add(BitmapFactory.decodeResource(mContext.resources, R.drawable.photo_3))
        mWidgetItems.add(BitmapFactory.decodeResource(mContext.resources, R.drawable.photo_4))
        mWidgetItems.add(BitmapFactory.decodeResource(mContext.resources, R.drawable.photo_5))
    }

    override fun onDestroy() {
    }

    override fun getCount(): Int = mWidgetItems.size

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(mContext.packageName, R.layout.widget_item)
        rv.setImageViewBitmap(R.id.imageView, mWidgetItems[position])

        val extras = bundleOf(ImageBannerWidget.EXTRA_ITEM to position)

        val fillIntent = Intent()
        fillIntent.putExtras(extras)

        rv.setOnClickFillInIntent(R.id.imageView, fillIntent)
        return rv
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = 0

    override fun hasStableIds(): Boolean = false
}