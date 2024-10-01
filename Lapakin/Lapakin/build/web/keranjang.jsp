<%-- 
    Document   : keranjang
    Created on : Jan 1, 2020, 8:55:38 PM
    Author     : HASiAN
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="obj.Produk"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--
=========================================================
 Material Dashboard - v2.1.1
=========================================================

 Product Page: https://www.creative-tim.com/product/material-dashboard
 Copyright 2019 Creative Tim (https://www.creative-tim.com)
 Licensed under MIT (https://github.com/creativetimofficial/material-dashboard/blob/master/LICENSE.md)

 Coded by Creative Tim

=========================================================

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. -->

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <title>
            Lapakin X PCR
        </title>
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no'
              name='viewport' />
        <!--     Fonts and icons     -->
        <link rel="stylesheet" type="text/css"
              href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
        <!-- CSS Files -->
        <link href="assets/css/material-dashboard.css?v=2.1.1" rel="stylesheet" />
        <!-- CSS Just for demo purpose, don't include it in your project -->
        <link href="assets/demo/demo.css" rel="stylesheet" />
    </head>
    <%
        List<Produk> p;
        if (request.getSession().getAttribute("cart") == null) {
            p = new ArrayList<Produk>();
        } else {
            p = (List<Produk>) request.getSession().getAttribute("cart");
        }
        double total = 0;

        for (int i = 0; i < p.size(); i++) {
            total += p.get(i).getJumlah() * p.get(i).getHarga();
        }
    %>
    <body>
        <div class="wrapper">
            <!-- Navbar -->
            <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top">
                <div class="container-fluid">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" aria-controls="navigation-index"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="navbar-toggler-icon icon-bar"></span>
                        <span class="navbar-toggler-icon icon-bar"></span>
                        <span class="navbar-toggler-icon icon-bar"></span>
                    </button>
                    <div class="collapse navbar-collapse justify-content-end">
                        <ul class="navbar-nav">
                            <div class="text">
                                <a href ="HomeServlet">
                                    Lapakin
                                </a>
                            </div>
                            <li class="nav-item">
                                <a href="ListTransaksiServlet" class="nav-link">
                                    <i class="material-icons">list</i>
                                    <p class="d-lg-none d-md-block">
                                        Stats
                                    </p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="KeranjangServlet" class="nav-link">
                                    <i class="material-icons">shopping_cart</i>
                                    <p class="d-lg-none d-md-block">
                                        Stats
                                    </p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <%if (request.getSession().getAttribute("login") == null) {%>
                                <a href="LoginServlet" class="nav-link">
                                    <i class="material-icons">launch</i>
                                    <p class="d-lg-none d-md-block">
                                        Stats
                                    </p>
                                </a>
                                <%} else {%>
                                <a href="LogoutServlet" class="nav-link">
                                    <i class="material-icons">launch</i>
                                    <p class="d-lg-none d-md-block">
                                        Stats
                                    </p>
                                </a>
                                <%}%>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

            <br /><br /><br /><br />

            <!-- End Navbar -->
            <div class="content">
                <div class="container-fluid">
                    <ul class="navbar-nav">
                        <div class="keranjang">
                            Keranjang
                        </div>
                        <br/>
                    </ul>
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header card-header-rose card-header-icon">
                                <div class="card-icon">
                                    <i class="material-icons">shopping_cart</i>
                                </div>
                                <h4 class="card-title">Keranjang</h4>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <!--<form action="PembayaranServlet" method="POST">-->
                                    <table class="table table-shopping">
                                        <thead>
                                            <tr>
                                                <th>No</th>
                                                <th colspan="2" style="padding-left: 45px;">Gambar</th>
                                                <th>Nama</th>
                                                <th>Harga</th>
                                                <th class="text-right">Jumlah</th>
                                                <th class="text-right">Total</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for (int i = 0; i < p.size(); i++) {%>
                                            <tr>
                                                <td><%=i + 1%></td>
                                                <td colspan="2">
                                                    <div class="img-container">
                                                        <img alt="..." src="assets/img/product.jpg">
                                                    </div>
                                                </td>
                                                <td class="td-name">
                                                    <a href="#"><%=p.get(i).getNama()%></a>
                                                    <br>
                                                </td>
                                                <td>
                                                    <small>Rp</small><%=p.get(i).getHarga()%>
                                                </td>
                                                <td class="td-number">
                                                    <%=(int) p.get(i).getJumlah()%>
                                                    <div class="btn-group btn-group-sm">
                                                        <a href="KeranjangServlet?action=kurang&id=<%=p.get(i).getId_produk()%>" class="btn btn-round btn-info"> <i class="material-icons">remove</i>
                                                        </a>
                                                        <a href="KeranjangServlet?action=beli&id=<%=p.get(i).getId_produk()%>" class="btn btn-round btn-info"> <i class="material-icons">add</i> </a>
                                                    </div>
                                                </td>
                                                <td class="td-number">
                                                    <small>Rp</small><%=(int) (p.get(i).getJumlah() * p.get(i).getHarga())%>
                                                </td>
                                                <td class="td-actions">
                                                    <a href="KeranjangServlet?action=hapus&id=<%=p.get(i).getId_produk()%>" title="" class="btn btn-link" type="button" data-original-title="Remove item"
                                                       data-placement="left" rel="tooltip">
                                                        <i class="material-icons">close</i>
                                                    </a>
                                                </td>
                                            </tr>
                                            <%}%>
                                            <!--                                            <tr>
                                                                                            <td>2</td>
                                                                                            <td colspan="2">
                                                                                                <div class="img-container">
                                                                                                    <img alt="..." src="https://demos.creative-tim.com/material-dashboard-pro/assets/img/card-1.jpg">
                                                                                                </div>
                                                                                            </td>
                                                                                            <td class="td-name">
                                                                                                <a href="#">Shampo</a>
                                                                                                <br>
                                                                                            </td>
                                                                                            <td>
                                                                                                <small>Rp</small>2000
                                                                                            </td>
                                                                                            <td class="td-number">
                                                                                                1
                                                                                                <div class="btn-group btn-group-sm">
                                                                                                    <button class="btn btn-round btn-info"> <i class="material-icons">remove</i>
                                                                                                    </button>
                                                                                                    <button class="btn btn-round btn-info"> <i class="material-icons">add</i> </button>
                                                                                                </div>
                                                                                            </td>
                                                                                            <td class="td-number">
                                                                                                <small>Rp</small>2000
                                                                                            </td>
                                                                                            <td class="td-actions">
                                                                                                <button title="" class="btn btn-link" type="button" data-original-title="Remove item" data-placement="left"
                                                                                                    rel="tooltip">
                                                                                                    <i class="material-icons">close</i>
                                                                                                </button>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td>3</td>
                                                                                            <td colspan="2">
                                                                                                <div class="img-container">
                                                                                                    <img alt="..." src="https://demos.creative-tim.com/material-dashboard-pro/assets/img/card-1.jpg">
                                                                                                </div>
                                                                                            </td>
                                                                                            <td class="td-name">
                                                                                                <a href="#">Beras</a>
                                                                                                <br>
                                                                                            </td>
                                                                                            <td>
                                                                                                <small>Rp</small>120000
                                                                                            </td>
                                                                                            <td class="td-number">
                                                                                                1
                                                                                                <div class="btn-group btn-group-sm">
                                                                                                    <button class="btn btn-round btn-info"> <i class="material-icons">remove</i>
                                                                                                    </button>
                                                                                                    <button class="btn btn-round btn-info"> <i class="material-icons">add</i> </button>
                                                                                                </div>
                                                                                            </td>
                                                                                            <td class="td-number">
                                                                                                <small>Rp</small>120000
                                                                                            </td>
                                                                                            <td class="td-actions">
                                                                                                <button title="" class="btn btn-link" type="button" data-original-title="Remove item" data-placement="left"
                                                                                                    rel="tooltip">
                                                                                                    <i class="material-icons">close</i>
                                                                                                </button>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td>4</td>
                                                                                            <td colspan="2">
                                                                                                <div class="img-container">
                                                                                                    <img alt="..." src="https://demos.creative-tim.com/material-dashboard-pro/assets/img/card-1.jpg">
                                                                                                </div>
                                                                                            </td>
                                                                                            <td class="td-name">
                                                                                                <a href="#">Deterjen</a>
                                                                                                <br>
                                                                                            </td>
                                                                                            <td>
                                                                                                <small>Rp</small>15000
                                                                                            </td>
                                                                                            <td class="td-number">
                                                                                                1
                                                                                                <div class="btn-group btn-group-sm">
                                                                                                    <button class="btn btn-round btn-info"> <i class="material-icons">remove</i>
                                                                                                    </button>
                                                                                                    <button class="btn btn-round btn-info"> <i class="material-icons">add</i> </button>
                                                                                                </div>
                                                                                            </td>
                                                                                            <td class="td-number">
                                                                                                <small>Rp</small>15000
                                                                                            </td>
                                                                                            <td class="td-actions">
                                                                                                <button title="" class="btn btn-link" type="button" data-original-title="Remove item" data-placement="left"
                                                                                                    rel="tooltip">
                                                                                                    <i class="material-icons">close</i>
                                                                                                </button>
                                                                                            </td>
                                                                                        </tr>-->
                                            <tr>
                                                <td colspan="5"></td>
                                                <td class="td-total">
                                                    Total
                                                </td>
                                                <td class="td-price" colspan="1">
                                                    <small>Rp</small><%=total%>
                                                </td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td colspan="4"></td>
                                                <td class="text-right" colspan="3">
                                                    <% if (p == null) {%>
                                                    <a href="HomeServlet" class="btn btn-rose btn-round" type="submit"><i
                                                            class="material-icons">keyboard_arrow_left</i>Tambah Belanjaan
                                                        <div class="ripple-container"></div>
                                                    </a>
                                                    <%} else {%>
                                                    <a href="TokoServlet?id_toko=<%=p.get(0).getId_toko()%> " class="btn btn-rose btn-round" type="submit"><i
                                                            class="material-icons">keyboard_arrow_left</i>Tambah Belanjaan
                                                        <div class="ripple-container"></div>
                                                    </a>
                                                    <%}%>
                                                    <a href="PembayaranServlet"class="btn btn-info btn-round" type="submit">Pembayaran<i
                                                            class="material-icons">keyboard_arrow_right</i>
                                                        <div class="ripple-container"></div>
                                                    </a>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <!--</form>-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <footer class="footer">
                <div class="container-fluid">
                    <nav class="float-left">
                    </nav>
                    <div class="copyright float-right">
                        &copy;
                        <script>
                            document.write(new Date().getFullYear())
                        </script> Lapakin X
                        <a href="https://pcr.ac.id" target="_blank">Politeknik Caltex Riau</a>.
                    </div>
                </div>
            </footer>
        </div>
    </div>
    <div class="fixed-plugin">
        <div class="dropdown show-dropdown">
            <a href="#" data-toggle="dropdown">
                <i class="fa fa-cog fa-2x"> </i>
            </a>
            <ul class="dropdown-menu">
                <li class="header-title"> Warna</li>
                <li class="adjustments-line">
                    <a href="javascript:void(0)" class="switch-trigger active-color">
                        <div class="badge-colors ml-auto mr-auto">
                            <span class="badge filter badge-purple" data-color="purple"></span>
                            <span class="badge filter badge-azure" data-color="azure"></span>
                            <span class="badge filter badge-green" data-color="green"></span>
                            <span class="badge filter badge-warning" data-color="orange"></span>
                            <span class="badge filter badge-danger" data-color="danger"></span>
                            <span class="badge filter badge-rose active" data-color="rose"></span>
                        </div>
                        <div class="clearfix"></div>
                    </a>
                </li>
                <li class="header-title">Gambar</li>
                <li class="active">
                    <a class="img-holder switch-trigger" href="javascript:void(0)">
                        <img src="assets/img/sidebar-1.jpg" alt="">
                    </a>
                </li>
                <li>
                    <a class="img-holder switch-trigger" href="javascript:void(0)">
                        <img src="assets/img/sidebar-2.jpg" alt="">
                    </a>
                </li>
                <li>
                    <a class="img-holder switch-trigger" href="javascript:void(0)">
                        <img src="assets/img/sidebar-3.jpg" alt="">
                    </a>
                </li>
                <li>
                    <a class="img-holder switch-trigger" href="javascript:void(0)">
                        <img src="assets/img/sidebar-4.jpg" alt="">
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!--   Core JS Files   -->
    <script src="assets/js/core/jquery.min.js"></script>
    <script src="assets/js/core/popper.min.js"></script>
    <script src="assets/js/core/bootstrap-material-design.min.js"></script>
    <script src="assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
    <!-- Plugin for the momentJs  -->
    <script src="assets/js/plugins/moment.min.js"></script>
    <!--  Plugin for Sweet Alert -->
    <script src="assets/js/plugins/sweetalert2.js"></script>
    <!-- Forms Validations Plugin -->
    <script src="assets/js/plugins/jquery.validate.min.js"></script>
    <!-- Plugin for the Wizard, full documentation here: https://github.com/VinceG/twitter-bootstrap-wizard -->
    <script src="assets/js/plugins/jquery.bootstrap-wizard.js"></script>
    <!--	Plugin for Select, full documentation here: http://silviomoreto.github.io/bootstrap-select -->
    <script src="assets/js/plugins/bootstrap-selectpicker.js"></script>
    <!--  Plugin for the DateTimePicker, full documentation here: https://eonasdan.github.io/bootstrap-datetimepicker/ -->
    <script src="assets/js/plugins/bootstrap-datetimepicker.min.js"></script>
    <!--  DataTables.net Plugin, full documentation here: https://datatables.net/  -->
    <script src="assets/js/plugins/jquery.dataTables.min.js"></script>
    <!--	Plugin for Tags, full documentation here: https://github.com/bootstrap-tagsinput/bootstrap-tagsinputs  -->
    <script src="assets/js/plugins/bootstrap-tagsinput.js"></script>
    <!-- Plugin for Fileupload, full documentation here: http://www.jasny.net/bootstrap/javascript/#fileinput -->
    <script src="assets/js/plugins/jasny-bootstrap.min.js"></script>
    <!--  Full Calendar Plugin, full documentation here: https://github.com/fullcalendar/fullcalendar    -->
    <script src="assets/js/plugins/fullcalendar.min.js"></script>
    <!-- Vector Map plugin, full documentation here: http://jvectormap.com/documentation/ -->
    <script src="assets/js/plugins/jquery-jvectormap.js"></script>
    <!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
    <script src="assets/js/plugins/nouislider.min.js"></script>
    <!-- Include a polyfill for ES6 Promises (optional) for IE11, UC Browser and Android browser support SweetAlert -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/core-js/2.4.1/core.js"></script>
    <!-- Library for adding dinamically elements -->
    <script src="assets/js/plugins/arrive.min.js"></script>
    <!--  Google Maps Plugin    -->
    <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
    <!-- Chartist JS -->
    <script src="assets/js/plugins/chartist.min.js"></script>
    <!--  Notifications Plugin    -->
    <script src="assets/js/plugins/bootstrap-notify.js"></script>
    <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
    <script src="assets/js/material-dashboard.js?v=2.1.1" type="text/javascript"></script>
    <!-- Material Dashboard DEMO methods, don't include it in your project! -->
    <script src="assets/demo/demo.js"></script>
    <script>
                            $(document).ready(function () {
                                $().ready(function () {
                                    $sidebar = $('.sidebar');

                                    $sidebar_img_container = $sidebar.find('.sidebar-background');

                                    $full_page = $('.full-page');

                                    $sidebar_responsive = $('body > .navbar-collapse');

                                    window_width = $(window).width();

                                    fixed_plugin_open = $('.sidebar .sidebar-wrapper .nav li.active a p').jsp();

                                    if (window_width > 767 && fixed_plugin_open == 'Dashboard') {
                                        if ($('.fixed-plugin .dropdown').hasClass('show-dropdown')) {
                                            $('.fixed-plugin .dropdown').addClass('open');
                                        }

                                    }

                                    $('.fixed-plugin a').click(function (event) {
                                        // Alex if we click on switch, stop propagation of the event, so the dropdown will not be hide, otherwise we set the  section active
                                        if ($(this).hasClass('switch-trigger')) {
                                            if (event.stopPropagation) {
                                                event.stopPropagation();
                                            } else if (window.event) {
                                                window.event.cancelBubble = true;
                                            }
                                        }
                                    });

                                    $('.fixed-plugin .active-color span').click(function () {
                                        $full_page_background = $('.full-page-background');

                                        $(this).siblings().removeClass('active');
                                        $(this).addClass('active');

                                        var new_color = $(this).data('color');

                                        if ($sidebar.length != 0) {
                                            $sidebar.attr('data-color', new_color);
                                        }

                                        if ($full_page.length != 0) {
                                            $full_page.attr('filter-color', new_color);
                                        }

                                        if ($sidebar_responsive.length != 0) {
                                            $sidebar_responsive.attr('data-color', new_color);
                                        }
                                    });

                                    $('.fixed-plugin .background-color .badge').click(function () {
                                        $(this).siblings().removeClass('active');
                                        $(this).addClass('active');

                                        var new_color = $(this).data('background-color');

                                        if ($sidebar.length != 0) {
                                            $sidebar.attr('data-background-color', new_color);
                                        }
                                    });

                                    $('.fixed-plugin .img-holder').click(function () {
                                        $full_page_background = $('.full-page-background');

                                        $(this).parent('li').siblings().removeClass('active');
                                        $(this).parent('li').addClass('active');


                                        var new_image = $(this).find("img").attr('src');

                                        if ($sidebar_img_container.length != 0 && $('.switch-sidebar-image input:checked').length != 0) {
                                            $sidebar_img_container.fadeOut('fast', function () {
                                                $sidebar_img_container.css('background-image', 'url("' + new_image + '")');
                                                $sidebar_img_container.fadeIn('fast');
                                            });
                                        }

                                        if ($full_page_background.length != 0 && $('.switch-sidebar-image input:checked').length != 0) {
                                            var new_image_full_page = $('.fixed-plugin li.active .img-holder').find('img').data('src');

                                            $full_page_background.fadeOut('fast', function () {
                                                $full_page_background.css('background-image', 'url("' + new_image_full_page + '")');
                                                $full_page_background.fadeIn('fast');
                                            });
                                        }

                                        if ($('.switch-sidebar-image input:checked').length == 0) {
                                            var new_image = $('.fixed-plugin li.active .img-holder').find("img").attr('src');
                                            var new_image_full_page = $('.fixed-plugin li.active .img-holder').find('img').data('src');

                                            $sidebar_img_container.css('background-image', 'url("' + new_image + '")');
                                            $full_page_background.css('background-image', 'url("' + new_image_full_page + '")');
                                        }

                                        if ($sidebar_responsive.length != 0) {
                                            $sidebar_responsive.css('background-image', 'url("' + new_image + '")');
                                        }
                                    });

                                    $('.switch-sidebar-image input').change(function () {
                                        $full_page_background = $('.full-page-background');

                                        $input = $(this);

                                        if ($input.is(':checked')) {
                                            if ($sidebar_img_container.length != 0) {
                                                $sidebar_img_container.fadeIn('fast');
                                                $sidebar.attr('data-image', '#');
                                            }

                                            if ($full_page_background.length != 0) {
                                                $full_page_background.fadeIn('fast');
                                                $full_page.attr('data-image', '#');
                                            }

                                            background_image = true;
                                        } else {
                                            if ($sidebar_img_container.length != 0) {
                                                $sidebar.removeAttr('data-image');
                                                $sidebar_img_container.fadeOut('fast');
                                            }

                                            if ($full_page_background.length != 0) {
                                                $full_page.removeAttr('data-image', '#');
                                                $full_page_background.fadeOut('fast');
                                            }

                                            background_image = false;
                                        }
                                    });

                                    $('.switch-sidebar-mini input').change(function () {
                                        $body = $('body');

                                        $input = $(this);

                                        if (md.misc.sidebar_mini_active == true) {
                                            $('body').removeClass('sidebar-mini');
                                            md.misc.sidebar_mini_active = false;

                                            $('.sidebar .sidebar-wrapper, .main-panel').perfectScrollbar();

                                        } else {

                                            $('.sidebar .sidebar-wrapper, .main-panel').perfectScrollbar('destroy');

                                            setTimeout(function () {
                                                $('body').addClass('sidebar-mini');

                                                md.misc.sidebar_mini_active = true;
                                            }, 300);
                                        }

                                        // we simulate the window Resize so the charts will get updated in realtime.
                                        var simulateWindowResize = setInterval(function () {
                                            window.dispatchEvent(new Event('resize'));
                                        }, 180);

                                        // we stop the simulation of Window Resize after the animations are completed
                                        setTimeout(function () {
                                            clearInterval(simulateWindowResize);
                                        }, 1000);

                                    });
                                });
                            });
    </script>
</body>

</html>
