<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<tiles:insert page="template.jsp" flush="true">
  <tiles:put name="logo" value="logo.jsp" />
  <tiles:put name="footer" value="footer.jsp" />
  <tiles:put name="menu"   value="menu.jsp" />
  <tiles:put name="body"   value="body.jsp" />
</tiles:insert>

<!-- http://www-igm.univ-mlv.fr/~dr/XPOSE2003/tiles/taglib.html#2 -->