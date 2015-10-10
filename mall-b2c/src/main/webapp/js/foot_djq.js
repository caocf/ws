document.write(" <script language=\"javascript\" src=\"http://www.12580life.com/foot.jsp\" > <\/script>");

jQuery(document).ready(function(){
	  jQuery("#mosh_12580_header #city #citylist a").click(function(){
			jQuery.cookie('areaCode',jQuery(this).attr("acode"), {
				expires : 90,
				domain: '12580life.com',
				path:'/'
			});
			jQuery.cookie('regionCode',jQuery(this).attr("rcode"), {
				expires : 90,
				domain: '12580life.com',
				path:'/'
			});
			location.href='http://quan.12580life.com/'+jQuery(this).attr("spell");
		});
	 
  });
