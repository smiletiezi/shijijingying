<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.py.shijijingying.utils.Utils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="user" value="${sessionScope.currentUser}"/>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
        <div class="layui-header" >
        <!-- 头部区域 -->
        <ul class="layui-nav layui-layout-left">
          <li class="layui-nav-item layadmin-flexible" lay-unselect>
            <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
              <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
            </a>
          </li>
     
          <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;" layadmin-event="refresh" title="刷新">
              <i class="layui-icon layui-icon-refresh-1"></i>
            </a>
          </li>
           <%-- <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;">
                <img src="${ctx}/static/img/homeLogo.png" style=" margin-top: -3px;" />
            </a>
          </li> --%>
        </ul>
        <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">
          
         <!--  <li class="layui-nav-item" lay-unselect>
            <a lay-href="javascript:;" layadmin-event="message" lay-text="消息中心">
              <i class="layui-icon layui-icon-notice"></i>  
              
              如果有新消息，则显示小圆点
              <span class="layui-badge-dot"></span>
            </a>
          </li> -->
          <!-- <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="theme">
              <i class="layui-icon layui-icon-theme"></i>
            </a>
          </li> -->
         
          <%-- <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;">
              <cite>贤心</cite>
            </a>
            <dl class="layui-nav-child">
              <dd><a lay-href="javascript:;">基本资料</a></dd>
              <dd><a lay-href="javascript:;">修改密码</a></dd>
              <hr>
              <dd layadmin-event="logout" style="text-align: center;"><a lay-href="${ctx}/logout">退出</a></dd>
            </dl>
          </li> --%>
          	<!--修改密码 -->
           <li class="layui-nav-item layui-hide-xs" lay-unselect>
	            <a href="javascript:;">
	     		<i class="layui-icon layui-icon-username"></i>        
	       		 ${user.loginName}
	       		 </a>
          	</li>
        	<!--修改密码 -->
           <li class="layui-nav-item layui-hide-xs" lay-unselect>
           		<!-- JS在index页面 -->
	            <a href="javascript:;" onclick="updatePassWord();">
	            <i class="layui-icon layui-icon-password"></i>   
	           	修改密码
	           	</a>
          	</li>
          	<!-- 退出 -->
          	<li class="layui-nav-item layui-hide-xs" lay-unselect>
	            <a lay-href="${ctx}/logout" >
	            <i class="layui-icon layui-icon-close-fill"></i> 
	           	退出
	           	</a>
          	</li>
          
         <!--  <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="about"><i class="layui-icon layui-icon-more-vertical"></i></a>
          </li> -->
          <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
            <a href="javascript:;" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
          </li>
        </ul>
      </div>
</html>