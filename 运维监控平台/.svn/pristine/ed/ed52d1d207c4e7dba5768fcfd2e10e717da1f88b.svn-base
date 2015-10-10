<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="inner_title" value="经分报表" />
<%@ include file="../../includes/t.jsp"%>
<script type="text/javascript">
	$(function() {
		$('button[name=btn_export]').click(function() {
			var year = $('select[name=year]').val();
			var month = $('select[name=month]').val();
			var type = $(this).attr("export_type");
			if (year == "" || year == null || month == "" || month == null) {
				alert('请选择要导出数据的年月');
			} else {
				$('#export_year').val(year);
				$('#export_month').val(month);
				$('#export_type').val(type);
				$('#exportForm').submit();
			}

		});
	})
</script>

<div class="row-fluid">
	<div class="span12">
		<!-- 导入头部 -->
		<%@ include file="top.jsp"%>
		<!-- 导入头部结束 -->

		<div name="analysis" id="analysis_ywsr" style="overflow: auto;">
			<c:if test="${empty analysis_ywsr}">
				<div class="alert alert-info" style="margin-top: 20px;">
					<strong> <i class="icon24 i-info"></i> 当前没有相关记录！
					</strong>
				</div>
			</c:if>
			<c:if test="${not empty analysis_ywsr}">
				<div style="text-align: right; margin: 15px 0px">
					<button type="button" class="btn" export_type="ywsr"
						name="btn_export">导出到EXCEL文件</button>
				</div>
				<table class="table table-bordered table-hover table-striped">
					<thead>
						<tr>
							<c:forEach items="${analysis_ywsr}" var="list" varStatus="ywsr">
								<c:if test="${ywsr.index == 0 }">
									<c:forEach items="${list}" var="item" varStatus="itemindex">
										<c:choose>
											<c:when test="${itemindex.index == 0 }">
												<th></th>
												<th></th>
											</c:when>
											<c:otherwise>
												<th>${item}</th>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${analysis_ywsr}" var="list" varStatus="ywsr">
							<c:if test="${ywsr.count % 3 == 2 }">
								<fmt:formatNumber type='number' value='${ywsr.count / 3 - 1}'
									maxFractionDigits="0" var="ywsr_index"></fmt:formatNumber>
								<tr>
									<c:set value="${ywsr_left[ywsr_index] }" var="key"></c:set>
									<td nowrap="nowrap" rowspan="4"
										style="text-align: center; vertical-align: middle; border-right-color: #DDDDDD; border-right-style: solid; border-right-width: 1px;"
										title="${ywsr_hint[key] }">${ywsr_left[ywsr_index] }</td>
								</tr>
							</c:if>
							<c:if test="${ywsr.index != 0 }">
								<tr>
									<c:forEach items="${list}" var="item" varStatus="itemindex">
										<td nowrap="nowrap"
											<c:if test="${itemindex.index == 0 }">title="${ywsr_hint[item] }"</c:if>>${item
											}</td>
									</c:forEach>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>

		<!-- 定制会员列表 -->
		<div name="analysis" id="analysis_dzhy"
			style="display: none; overflow: auto;">
			<c:if test="${empty analysis_dzhy}">
				<div class="alert alert-info" style="margin-top: 20px;">
					<strong> <i class="icon24 i-info"></i> 当前没有相关记录！
					</strong>
				</div>
			</c:if>
			<c:if test="${not empty analysis_dzhy}">
				<div style="text-align: right; margin: 15px 0px">
					<button type="button" class="btn" export_type="dzhy"
						name="btn_export">导出到EXCEL文件</button>
				</div>
				<table class="table table-bordered table-hover table-striped">
					<thead>
						<tr>
							<c:forEach items="${analysis_dzhy}" var="list" varStatus="dzhy">
								<c:if test="${dzhy.index == 0 }">
									<c:forEach items="${list}" var="item" varStatus="itemindex">
										<c:choose>
											<c:when test="${itemindex.index == 0 }">
												<th></th>
												<th></th>
											</c:when>
											<c:otherwise>
												<th>${item}</th>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${analysis_dzhy}" var="list" varStatus="dzhy">
							<c:if test="${dzhy.count % 3 == 2 }">
								<fmt:formatNumber type='number' value='${dzhy.count / 3 - 1}'
									maxFractionDigits="0" var="dzhy_index"></fmt:formatNumber>
								<tr>
									<td nowrap="nowrap" rowspan="4"
										style="text-align: center; vertical-align: middle; border-right-color: #DDDDDD; border-right-style: solid; border-right-width: 1px;">${dzhy_left[dzhy_index]
										}</td>
								</tr>
							</c:if>
							<c:if test="${dzhy.index != 0 }">
								<tr>
									<c:forEach items="${list}" var="item" varStatus="itemindex">
										<td nowrap="nowrap"
											<c:if test="${itemindex.index == 0 }">title="${dzhy_hint[item] }"</c:if>>${item
											}</td>
									</c:forEach>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
		<!-- 定制会员结束 -->

		<!-- 月交易用户数 -->
		<div name="analysis" id="analysis_jyyh"
			style="display: none; overflow: auto;">
			<c:if test="${empty analysis_jyyh}">
				<div class="alert alert-info" style="margin-top: 20px;">
					<strong> <i class="icon24 i-info"></i> 当前没有相关记录！
					</strong>
				</div>
			</c:if>
			<c:if test="${not empty analysis_jyyh}">
				<div style="text-align: right; margin: 15px 0px">
					<button type="button" class="btn" export_type="jyyh"
						name="btn_export">导出到EXCEL文件</button>
				</div>
				<table class="table table-bordered table-hover table-striped">
					<thead>
						<tr>
							<c:forEach items="${analysis_jyyh}" var="list" varStatus="jyyh">
								<c:if test="${jyyh.index == 0 }">
									<c:forEach items="${list}" var="item" varStatus="itemindex">
										<c:choose>
											<c:when test="${itemindex.index == 0 }">
												<th></th>
											</c:when>
											<c:otherwise>
												<th>${item}</th>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${analysis_jyyh}" var="list" varStatus="jyyh">
							<%-- <c:if test="${jyyh.count % 3 == 2 }">
							<fmt:formatNumber type='number' value='${jyyh.count / 3 - 1}' maxFractionDigits="0" var="jyyh_index"></fmt:formatNumber> 
								<tr>
									<td nowrap="nowrap" rowspan="4" style="text-align: center; vertical-align: middle;">${jyyh_left[jyyh_index] }  </td>
								</tr>
							</c:if> --%>
							<c:if test="${jyyh.index != 0 && jyyh.index < 4}">
								<tr>
									<c:forEach items="${list}" var="item" varStatus="itemindex">
										<td nowrap="nowrap"
											<c:if test="${itemindex.index == 0 }">title="${jyyh_hint[item] }"</c:if>>${item
											}</td>
									</c:forEach>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
		<!-- 月交易用户数 结束 -->

		<!-- 客户端安装数 -->
		<div name="analysis" id="analysis_khd"
			style="display: none; overflow: auto;">
			<c:if test="${empty analysis_khd}">
				<div class="alert alert-info" style="margin-top: 20px;">
					<strong> <i class="icon24 i-info"></i> 当前没有相关记录！
					</strong>
				</div>
			</c:if>
			<c:if test="${not empty analysis_khd}">
				<div style="text-align: right; margin: 15px 0px">
					<button type="button" class="btn" export_type="khd"
						name="btn_export">导出到EXCEL文件</button>
				</div>
				<table class="table table-bordered table-hover table-striped">
					<thead>
						<tr>
							<c:forEach items="${analysis_khd}" var="list" varStatus="khd">
								<c:if test="${khd.index == 0 }">
									<c:forEach items="${list}" var="item" varStatus="itemindex">
										<c:choose>
											<c:when test="${itemindex.index == 0 }">
												<th></th>
												<th></th>
											</c:when>
											<c:otherwise>
												<th>${item}</th>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${analysis_khd}" var="list" varStatus="khd">
							<c:if test="${khd.count % 3 == 2 }">
								<fmt:formatNumber type='number' value='${khd.count / 3 - 1}'
									maxFractionDigits="0" var="khd_index"></fmt:formatNumber>
								<tr>
									<c:set value="${khd_left[khd_index] }" var="key"></c:set>
									<td nowrap="nowrap" rowspan="4"
										style="text-align: center; vertical-align: middle; border-right-color: #DDDDDD; border-right-style: solid; border-right-width: 1px;"
										title="${khd_hint[key] }">${khd_left[khd_index] }</td>
								</tr>
							</c:if>
							<c:if test="${khd.index != 0 }">
								<tr>
									<c:forEach items="${list}" var="item" varStatus="itemindex">
										<td nowrap="nowrap"
											<c:if test="${itemindex.index == 0 }">title="${khd_hint[item] }"</c:if>>${item
											}</td>
									</c:forEach>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
		<!-- 客户端安装数 结束 -->

		<!-- 电商交易额_后加需求 -->
		<div name="analysis" id="analysis_dsjy_jsuss"
			style="display: none; overflow: auto;">
			<c:if test="${empty analysis_dsjy_jsuss}">
				<div class="alert alert-info" style="margin-top: 20px;">
					<strong> <i class="icon24 i-info"></i> 当前没有相关记录！
					</strong>
				</div>
			</c:if>
			<c:if test="${not empty analysis_dsjy_jsuss}">
				<div style="text-align: right; margin: 15px 0px">
					<button type="button" class="btn" export_type="dsjy_jsuss"
						name="btn_export">导出到EXCEL文件</button>
				</div>
				<table class="table table-bordered table-hover table-striped">
					<thead>
						<tr>
							<c:forEach items="${analysis_dsjy_jsuss}" var="list"
								varStatus="dsjy_jsuss">
								<c:if test="${dsjy_jsuss.index == 0 }">
									<c:forEach items="${list}" var="item" varStatus="itemindex">
										<c:choose>
											<c:when test="${itemindex.index == 0 }">
												<th colspan="2"></th>
											</c:when>
											<c:otherwise>
												<th>${item}</th>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${analysis_dsjy_jsuss}" var="list"
							varStatus="dsjy_jsuss">
							<c:if test="${dsjy_jsuss.index != 0 }">
								<tr>
									<c:forEach items="${list}" var="item" varStatus="itemindex">
										<c:choose>
											<c:when
												test="${dsjy_jsuss.index == 1 && itemindex.index == 0 }">
												<td rowspan="3"
													style="text-align: center; vertical-align: middle;background-color: #F7F7F7 !important;" title="通过清结算支付">清算平台</td>
											</c:when>
											<c:when
												test="${dsjy_jsuss.index == 4 && itemindex.index == 0 }">
												<td rowspan="3"
													style="text-align: center; vertical-align: middle;background-color: #F7F7F7 !important;" title="未通过清结算平台支付">其他平台</td>
											</c:when>
											<c:when
												test="${dsjy_jsuss.index == 7 && itemindex.index == 0 }">
												<td rowspan="3"
													style="text-align: center; vertical-align: middle;background-color: #F7F7F7 !important;">总计</td>
											</c:when>
										</c:choose>
										<td style="word-break: keep-all;">${item}</td>
									</c:forEach>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>

		<!-- 电商交易额_后加需求结束 -->

		<!-- 活跃用户数 -->
		<div name="analysis" id="analysis_hyyh"
			style="display: none; overflow: auto;">
			<c:if test="${empty analysis_hyyh}">
				<div class="alert alert-info" style="margin-top: 20px;">
					<strong> <i class="icon24 i-info"></i> 当前没有相关记录！
					</strong>
				</div>
			</c:if>
			<c:if test="${not empty analysis_hyyh}">
				<div style="text-align: right; margin: 15px 0px">
					<button type="button" class="btn" export_type="hyyh"
						name="btn_export">导出到EXCEL文件</button>
				</div>
				<table class="table table-bordered table-hover table-striped">
					<thead>
						<tr>
							<c:forEach items="${analysis_hyyh}" var="list" varStatus="hyyh">
								<c:if test="${hyyh.index == 0 }">
									<c:forEach items="${list}" var="item" varStatus="itemindex">
										<c:choose>
											<c:when test="${itemindex.index == 0 }">
												<th></th>
											</c:when>
											<c:otherwise>
												<th>${item}</th>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${analysis_hyyh}" var="list" varStatus="hyyh">
							<%-- <c:if test="${hyyh.count % 3 == 2 }">
							<fmt:formatNumber type='number' value='${hyyh.count / 3 - 1}' maxFractionDigits="0" var="hyyh_index"></fmt:formatNumber> 
								<tr>
									<td nowrap="nowrap" rowspan="4" style="text-align: center; vertical-align: middle;">${hyyh_left[hyyh_index] }  </td>
								</tr>
							</c:if> --%>
							<c:if test="${hyyh.index == 1 || hyyh.index == 2}">
								<tr>
									<c:forEach items="${list}" var="item" varStatus="itemindex">
										<td nowrap="nowrap"
											<c:if test="${itemindex.index == 0 }">title="${hyyh_hint[item] }"</c:if>>${item
											}</td>
									</c:forEach>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
		<!-- 活跃用户数结束 -->
	</div>
</div>
<%@ include file="../../includes/b.jsp"%>