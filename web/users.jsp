<%-- 
    Document   : dashbord
    Created on : 13 juil. 2021, 19:21:31
    Author     : xpro
--%>

<%@page import="MODEL.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%
    String userName = "";
    int userId = 0;
    boolean isAdmin = false;
    if(session.getAttribute("user") != null) {
        User usr = (User)session.getAttribute("user");
        userName = usr.getUser_name();
        userId = usr.getId();
        isAdmin = usr.getIs_admin();
    }
    if(session.getAttribute("user") == null || isAdmin == false) {
        response.sendRedirect("login");
    }
%>

<!DOCTYPE html>
<html class="loading dark-layout" lang="en" data-layout="dark-layout" data-textdirection="ltr">
<!-- BEGIN: Head-->
<jsp:include page="include/head.jsp"/>
<!-- END: Head-->

<!-- BEGIN: Body-->

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click"
      data-menu="vertical-menu-modern" data-col="">

<!-- BEGIN: Header-->
<jsp:include page="include/header.jsp">
    <jsp:param name="username" value="<%=userName%>" />
    <jsp:param name="userId" value="<%=userId%>" />
</jsp:include>
<!-- END: Header-->

<!-- BEGIN: Main Menu-->
<jsp:include page="include/navigation.jsp">
    <jsp:param name="activeNav" value="users" />
</jsp:include>
<!-- END: Main Menu-->

<!-- BEGIN: Content-->
<div class="app-content content ">
    <div class="content-overlay"></div>
    <div class="header-navbar-shadow"></div>
    <div class="content-wrapper">
        <div class="content-header row"></div>
        <div class="content-body">
            <div class="row" id="basic-table">
                <div class="col-12">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title">Users</h4>
                            <div class="dt-buttons btn-group flex-wrap">
                                <button class="btn add-new btn-primary mt-50" tabindex="0" aria-controls="DataTables_Table_0" type="button" onclick="openModalFormUser(SUBMIT_MODE.ADD)">
                                    <span>Add New User</span>
                                </button>
                            </div>
                        </div>
                        <div class="table-responsive">
                            <table id="users-table" class="table">
                                <thead>
                                <tr>
                                    <th>Full Name</th>
                                    <th>Username</th>
                                    <th>Email</th>
                                    <th>Admin</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END: Content-->

<!-- Add user Modal -->
<div class="modal fade text-left" id="modalFormUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel18" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalFormUserHeading">Add New User</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="POST" id="formUser" action="/arbeit-j2ee/users" class="needs-validation" enctype="multipart/form-data">
                <div class="modal-body">
                    <input type="hidden" name="id">
                    <div class="form-group">
                        <label for="fullName">Full Name</label>
                        <input type="text" class="form-control" name="fullName" id="fullName" placeholder="Full Name">
                        <span class="invalid-feedback">This field is required</span>
                    </div>
                    <div class="form-group">
                        <label for="UserName">UserName</label>
                        <input type="text" class="form-control" name="username" id="UserName" placeholder="Username">
                        <span class="invalid-feedback">This field is required</span>
                    </div>
                    <div class="form-group">
                        <label for="Email">Email</label>
                        <input type="email" class="form-control" name="email" id="Email" placeholder="Email">
                        <span class="invalid-feedback">This field is required</span>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                        <span class="invalid-feedback">This field is required</span>
                    </div>
                    <div class="form-group">
                        <label for="password">Confirm Password</label>
                        <input type="password" class="form-control" name="confirm-password" id="confirm-password" placeholder="Confirm Password">
                        <span class="invalid-feedback">Confirm password do not match</span>
                    </div>
                    <div class="form-group" style="margin-bottom: -8px;">
                        <label for="ContactName">Avatar</label>
                        <input type="file" class="form-control" name="avatar" id="avatar" accept="image/jpeg">
                        <span id="avatar_error" class="avatar"></span>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="isAdmin" id="isAmin">
                        <label class="form-check-label" for="isAmin">Admin</label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="sidenav-overlay"></div>
<div class="drag-target"></div>

<!-- BEGIN: Footer-->
<footer class="footer footer-static footer-light">
    <p class="clearfix mb-0"><span
            class="float-md-left d-block d-md-inline-block mt-25">COPYRIGHT &copy; 2021 Arbeit <span
            class="d-none d-sm-inline-block">, All rights Reserved</span></span></p>
</footer>
<button class="btn btn-primary btn-icon scroll-top" type="button"><i data-feather="arrow-up"></i></button>
<!-- END: Footer-->


<!-- BEGIN: Vendor JS-->
<script src="asset/app-assets/vendors/js/vendors.min.js"></script>
<!-- BEGIN Vendor JS-->
<!-- BEGIN: Page Vendor JS-->
<script src="asset/app-assets/vendors/js/extensions/sweetalert2.all.min.js"></script>
<script src="asset/app-assets/vendors/js/pickers/flatpickr/flatpickr.min.js"></script>
<!-- END: Page Vendor JS-->

<!-- BEGIN: Theme JS-->
<script src="asset/app-assets/js/core/app-menu.js"></script>
<script src="asset/app-assets/js/core/app.js"></script>
<!-- END: Theme JS-->

<!-- BEGIN: Page JS-->
<script src="asset/app-assets/js/scripts/extensions/ext-component-sweet-alerts.js"></script>
<script src="assets/js/xhr.js"></script>
<script src="assets/js/users.js"></script>
<!-- END: Page JS-->

<script>
    $(window).on('load', function () {
        if (feather) {
            feather.replace({
                width: 14,
                height: 14
            });
        }
    })
</script>
</body>
<!-- END: Body-->

</html>