package org.eobjects.datacleaner.visualization

import org.eobjects.analyzer.beans.api.Renderer
import org.eobjects.analyzer.result.html.HtmlFragment
import org.eobjects.analyzer.beans.api.RendererBean
import org.eobjects.analyzer.result.renderer.HtmlRenderingFormat
import org.eobjects.analyzer.beans.api.RendererPrecedence
import org.eobjects.analyzer.result.html.HtmlRenderingContext
import org.eobjects.analyzer.result.html.SimpleHtmlFragment
import org.eobjects.analyzer.beans.api.RendererBean
import org.eobjects.analyzer.result.renderer.HtmlRenderingFormat
import org.eobjects.analyzer.result.html.HtmlRenderer

@RendererBean(classOf[HtmlRenderingFormat])
class ScatterAnalyzerResultHtmlRenderer extends HtmlRenderer[ScatterAnalyzerResult] {

  override def handleFragment(frag: SimpleHtmlFragment, result: ScatterAnalyzerResult, context: HtmlRenderingContext) {
    val elementId = context.createElementId()

    frag.addHeadElement(ScatterAnalyzerResuableChartHeadElement)
    frag.addHeadElement(new ScatterAnalyzerChartScriptHeadElement(result, elementId));

    val html =
      <div class="scatterAnalyzerDiv">
        <div class="scatterChart" id={ elementId }>
        </div>
      </div>

    frag.addBodyElement(html.toString)
  }
}