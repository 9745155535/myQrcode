package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class overtime_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!doctype html>\n");
      out.write("<html lang=\"en\" class=\"fullscreen-bg\">\n");
      out.write("\n");

	String basePath=request.getContextPath();
    

      out.write("\n");
      out.write("<head>\n");
      out.write("\t<title>OverTime</title>\n");
      out.write("\t<meta charset=\"utf-8\">\n");
      out.write("\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n");
      out.write("\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0\">\n");
      out.write("\t<!-- VENDOR CSS -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"assets/vendor/bootstrap/css/bootstrap.min.css\">\n");
      out.write("\t<link rel=\"stylesheet\" href=\"assets/vendor/font-awesome/css/font-awesome.min.css\">\n");
      out.write("\t<link rel=\"stylesheet\" href=\"assets/vendor/linearicons/style.css\">\n");
      out.write("\t<!-- MAIN CSS -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"assets/css/main.css\">\n");
      out.write("\t<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"assets/css/demo.css\">\n");
      out.write("\t<!-- GOOGLE FONTS -->\n");
      out.write("\t<link href=\"https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700\" rel=\"stylesheet\">\n");
      out.write("\t<!-- ICONS -->\n");
      out.write("\t<link rel=\"apple-touch-icon\" sizes=\"76x76\" href=\"assets/img/apple-icon.png\">\n");
      out.write("\t<link rel=\"icon\" type=\"image/png\" sizes=\"96x96\" href=\"assets/img/favicon.png\">\n");
      out.write("</head>\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("function button(){\n");
      out.write("\twindow.location.href=\"");
      out.print(basePath);
      out.write("/page-login.jsp\";\n");
      out.write("\treturn false;\n");
      out.write("}\n");
      out.write("</script>\n");
      out.write("<body>\n");
      out.write("\t<!-- WRAPPER -->\n");
      out.write("\t<div id=\"wrapper\">\n");
      out.write("\t\t<div class=\"vertical-align-wrap\">\n");
      out.write("\t\t\t<div class=\"vertical-align-middle\">\n");
      out.write("\t\t\t\t<div class=\"auth-box lockscreen clearfix\">\n");
      out.write("\t\t\t\t\t<div class=\"content\">\n");
      out.write("\t\t\t\t\t\t<h1 class=\"sr-only\">Klorofil - Free Bootstrap dashboard</h1>\n");
      out.write("\t\t\t\t\t\t<div class=\"logo text-center\"><img src=\"assets/img/logo-dark.png\" alt=\"Klorofil Logo\"></div>\n");
      out.write("\t\t\t\t\t\t<div class=\"user text-center\">\n");
      out.write("\t\t\t\t\t\t\t<img src=\"assets/img/qrcode.bmp\" width=\"120px\" class=\"img-circle\" alt=\"Avatar\">\n");
      out.write("\t\t\t\t\t\t\t<h2 class=\"name text-danger \">Over Time </h2>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<form onsubmit=\"return button();\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"text-center\" >\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<button class=\"btn btn-primary btn-block\" type=\"submit\">重新登陆</button>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\t<!-- END WRAPPER -->\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
