package com.dragon.ui.bottom_navigation_bar.utils

import android.content.Context
import com.opensource.svgaplayer.SVGACallback
import com.opensource.svgaplayer.SVGAImageView
import com.opensource.svgaplayer.SVGAParser
import com.opensource.svgaplayer.SVGAVideoEntity

/***
 *  create by DragonForest on 2021/8/12
 */
object SvgaHelper {
    var svgaParser: SVGAParser? = null

    fun showEffect(
        context: Context?,
        svgaRes: String,
        animationView: SVGAImageView?,
        loopCount: Int? = -1,
        callback: SvgaCallback? = null
    ) {
        animationView?.loops = loopCount?:1
        if (svgaParser == null) {
            svgaParser = SVGAParser(context)
        }
        svgaParser?.decodeFromAssets(svgaRes, object : SVGAParser.ParseCompletion {
            override fun onComplete(videoItem: SVGAVideoEntity) {
                animationView?.setVideoItem(videoItem);
                animationView?.stepToFrame(0, true);
                callback?.onParseFinish(true)
                animationView?.callback = object : SVGACallback {
                    override fun onFinished() {
                        callback?.onAnimateFinish(true)
                    }

                    override fun onPause() {
                    }

                    override fun onRepeat() {
                    }

                    override fun onStep(frame: Int, percentage: Double) {
                    }
                }
            }

            override fun onError() {
                callback?.onParseFinish(false)
            }
        })
    }

    interface SvgaCallback {
        fun onParseFinish(isSuccess: Boolean)
        fun onAnimateFinish(isSuccess: Boolean)
    }

}