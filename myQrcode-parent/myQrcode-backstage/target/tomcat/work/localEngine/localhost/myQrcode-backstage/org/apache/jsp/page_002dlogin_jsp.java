package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class page_002dlogin_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");

	String basePath=request.getContextPath();

      out.write("\n");
      out.write("<html lang=\"en\" class=\"fullscreen-bg\">\n");
      out.write("<head>\n");
      out.write("\t<title>Login</title>\n");
      out.write("\t<meta charset=\"utf-8\">\n");
      out.write("\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n");
      out.write("\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0\">\n");
      out.write("\t<!-- VENDOR CSS -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.print(basePath);
      out.write("/assets/css/bootstrap.min.css\">\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.print(basePath);
      out.write("/assets/vendor/font-awesome/css/font-awesome.min.css\">\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.print(basePath);
      out.write("/assets/vendor/linearicons/style.css\">\n");
      out.write("\t<!-- MAIN CSS -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.print(basePath);
      out.write("/assets/css/main.css\">\n");
      out.write("\t<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.print(basePath);
      out.write("/assets/css/demo.css\">\n");
      out.write("\t<!-- GOOGLE FONTS -->\n");
      out.write("\t<link href=\"https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700\" rel=\"stylesheet\">\n");
      out.write("\t<!-- ICONS -->\n");
      out.write("\t<link rel=\"apple-touch-icon\" sizes=\"76x76\" href=\"");
      out.print(basePath);
      out.write("/assets/img/apple-icon.png\">\n");
      out.write("\t<link rel=\"icon\" type=\"image/png\" sizes=\"96x96\" href=\"");
      out.print(basePath);
      out.write("/assets/img/favicon.png\">\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\t<script src=\"");
      out.print(basePath);
      out.write("/assets/vendor/jquery/jquery.min.js\"></script>\n");
      out.write("\t<script src=\"assets/vendor/bootstrap/js/bootstrap.min.js\"></script>\n");
      out.write("\t<script type=\"text/javascript\">\n");
      out.write("\tfunction login(){\n");
      out.write("\t\t$('#myModal').modal('show');\n");
      out.write("\t\t$.ajax({\n");
      out.write("\t\t\ttype: \"POST\",\n");
      out.write("\t\t\tasync:false,\n");
      out.write("\t\t\tdata:{\"userName\":$(\"#signin-email\").val(),\"passWord\":$(\"#signin-password\").val()},\n");
      out.write("\t\t\turl: \"");
      out.print(basePath);
      out.write("/mytest/test.action\",\n");
      out.write("\t\t\tdataType: \"json\",\n");
      out.write("\t\t\tsuccess: function(data, textStatus) {  \n");
      out.write("\t\t\t//\t\t$(\"#test\").html(data.userName);\n");
      out.write("\t\t\t\tif(data.judge==false){\n");
      out.write("\t\t\t\t\t$(\".modal-body\").html(\"账号密码错误\");\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t}else{\n");
      out.write("\t\t\t\t\t$(\".modal-body\").html(\"登陆成功\");\n");
      out.write("\t\t\t\t\twindow.location.href =\"");
      out.print(basePath);
      out.write("/mytest/home.action\";\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t},\n");
      out.write("\t\t\terrer:function(){\n");
      out.write("\t\t\t\talert(\"网络错误\");\n");
      out.write("\t\t\t}\n");
      out.write("\t\t})\n");
      out.write("\t\treturn false;\n");
      out.write("\t}\n");
      out.write("\t\n");
      out.write("\t\t\n");
      out.write("\t</script>\n");
      out.write("\t<!-- WRAPPER -->\n");
      out.write("\t<div id=\"wrapper\">\n");
      out.write("\t\t<div class=\"vertical-align-wrap\">\n");
      out.write("\t\t\t<div class=\"vertical-align-middle\">\n");
      out.write("\t\t\t\t<div class=\"auth-box\">\n");
      out.write("\t\t\t\t\t<div class=\"left\">\n");
      out.write("\t\t\t\t\t\t<div class=\"content\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"header\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"logo text-center\"><img src=\"");
      out.print(basePath);
      out.write("/assets/img/logo-dark.png\" alt=\"Klorofil Logo\"></div>\n");
      out.write("\t\t\t\t\t\t\t\t<p id=\"errerInfo\" class=\"lead\">Login to your account</p>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t<form class=\"form-auth-small\"  method=\"post\" onsubmit=\"return login();\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<label for=\"signin-email\" class=\"control-label sr-only\">username</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\"  class=\"form-control\" id=\"signin-email\"  name=\"userName\" placeholder=\"Username\">\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<label for=\"signin-password\" class=\"control-label sr-only\">Password</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"password\" class=\"form-control\" id=\"signin-password\" name=\"passWord\" placeholder=\"Password\">\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"form-group clearfix\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<label class=\"fancy-checkbox element-left\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input type=\"checkbox\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<span>Remember me</span>\n");
      out.write("\t\t\t\t\t\t\t\t\t</label>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t<button type=\"submit\"   class=\"btn btn-primary btn-lg btn-block\">登陆</button>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"bottom\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<span class=\"helper-text\"><i class=\"fa fa-lock\"></i> <a href=\"#\">忘记密码?</a></span>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<div class=\"right\">\n");
      out.write("\t\t\t\t\t\t<div class=\"overlay\"></div>\n");
      out.write("\t\t\t\t\t\t<div class=\"content text\">\n");
      out.write("\t\t\t\t\t\t\t<h1 class=\"heading\">世界可见与不可见的真实。</h1>\n");
      out.write("\t\t\t\t\t\t\t<p>by The Develovers</p>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<div class=\"clearfix\"></div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<!-- 模态框（Modal） -->\n");
      out.write("<div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n");
      out.write("    <div class=\"modal-dialog\">\n");
      out.write("        <div class=\"modal-content\">\n");
      out.write("            <div class=\"modal-header\">\n");
      out.write("                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n");
      out.write("               \n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-body text-center\">\n");
      out.write("            \t正在登陆中<i class=\"fa fa-spinner fa-spin\"></i>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-footer\">\n");
      out.write("                <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\n");
      out.write("            </div>\n");
      out.write("        </div><!-- /.modal-content -->\n");
      out.write("    </div><!-- /.modal -->\n");
      out.write("\t</div>\n");
      out.write("\t<!-- END WRAPPER -->\n");
      out.write("</div>\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\t\t\n");
      out.write("</script>\n");
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
