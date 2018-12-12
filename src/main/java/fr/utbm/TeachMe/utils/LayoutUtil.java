package fr.utbm.TeachMe.utils;

public class LayoutUtil {
    private static String headerStr = "<head><meta charset=\"utf-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\"><meta name=\"description\" content=\"\"><meta name=\"author\" content=\"\"><title>TeachMe</title><link href=\"vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\"><link href=\"vendor/fontawesome-free/css/all.min.css\" rel=\"stylesheet\" type=\"text/css\"><link href=\"vendor/datatables/dataTables.bootstrap4.css\" rel=\"stylesheet\"><link href=\"css/sb-admin.css\" rel=\"stylesheet\"><link href=\"css/leaflet.css\" rel=\"stylesheet\"><link href=\"css/bootstrap-datepicker.min.css\" rel=\"stylesheet\"><link href=\"css/custom.css\" rel=\"stylesheet\"></head>";
    private static String footerStr = "<footer class=\"sticky-footer\"><div class=\"container my-auto\"><div class=\"copyright text-center my-auto\"><span>Projet LO54 - UTBM - Jacques Martinelli - Rémi Pereira</span></div></div></footer>";
    private static String jsIncludeStr = "<script src=\"vendor/jquery/jquery.min.js\"></script><script src=\"js/jquery.form.js\"></script><script src=\"vendor/bootstrap/js/bootstrap.bundle.min.js\"></script><script src=\"vendor/jquery-easing/jquery.easing.min.js\"></script><script src=\"vendor/chart.js/Chart.min.js\"></script><script src=\"vendor/datatables/jquery.dataTables.js\"></script><script src=\"vendor/datatables/dataTables.bootstrap4.js\"></script><script src=\"js/sb-admin.min.js\"></script><script src=\"js/jquery-dateformat.min.js\"></script><script src=\"js/bootstrap-datepicker.min.js\"></script>";
    private static String navBarStr  = "<nav class=\"navbar navbar-expand navbar-dark bg-dark static-top\"><a class=\"navbar-brand mr-1\" href=\"index.jsp\">LO54 Project</a><button class=\"btn btn-link btn-sm text-white order-1 order-sm-0\" id=\"sidebarToggle\" href=\"#\"><i class=\"fas fa-bars\"></i></button></nav>";
    private static String openBodyAndMenuStr = "";

    public static String getHeaderStr(){return LayoutUtil.headerStr;}
    public static String getFooterStr(){return LayoutUtil.footerStr;}
    public static String getjsIncludeStr(){return LayoutUtil.jsIncludeStr;}
    public static String getNavBarStr(){return LayoutUtil.navBarStr;}
    public static String getOpenBodyAndMenuStr(){return LayoutUtil.openBodyAndMenuStr;}

}
