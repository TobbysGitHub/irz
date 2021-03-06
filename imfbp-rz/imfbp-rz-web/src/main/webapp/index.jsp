
<div class="app app-header-fixed app-aside-fixed ">

	<!-- header -->
	#parse("/WEB-INF/vm/top.vm")
	<!-- / header -->

	<!-- aside -->
	#parse("/WEB-INF/vm/left.vm")
	<!-- / aside -->

	<!-- content -->
	<div id="content" class="app-content" role="main">
		<div class="app-content-full" style="overflow-y: hidden;">
			<!-- hbox layout -->
			<div class="hbox hbox-auto-xs bg-light ">
				<!-- column -->
				<div class="col">
					<div class="vbox">
						<div class="row-row">
							<div class="cell" style="overflow-y: hidden;">
								<div class="cell-inner">
									<div class="wrapper-md"
										style="width: 100%; height: 100%; padding: 0">

										<!-- tab start -->
										<div id="tabNav">
											<div id="tabs2" style="margin: 0">
												<ul style="padding: 0;" class="tab-ul">
													<li><a href="#tabs-1">Nunc tincidunt</a></li>
												</ul>
												<div id="tab-content">
													<div id="tabs-1">
														<iframe width="100%" height="100%" frameborder="0"
															src="http://ibop.yonyou.com:8080/imfbp-demo-web/todemoGrid"></iframe>
													</div>
												</div>

												<div class="Hui-tabNav-more btn-group">
													<a id="js-tabNav-prev"
														class="btn radius btn-default size-S" href="javascript:;">
														<i class="glyphicon glyphicon-chevron-left"></i>
													</a> <a id="js-tabNav-next"
														class="btn radius btn-default size-S" href="javascript:;">
														<i class="glyphicon glyphicon-chevron-right"></i>
													</a>
												</div>
											</div>
										</div>
										<!-- tab end -->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /column -->
			</div>
			<!-- /hbox layout -->
		</div>
	</div>
	<!-- / content -->

	<!-- footer -->
	<footer id="footer" class="app-footer" role="footer">
		<div class="wrapper b-t bg-light">
			<span class="pull-right">2.0.1 <a href ui-scroll="app"
				class="m-l-sm text-muted"><i class="fa fa-long-arrow-up"></i></a></span>
			&copy; 2015 Copyright.
		</div>
	</footer>
	<!-- / footer -->

	<script src="$homeModule.getTarget('/misc/js/ui-load.js')"></script>
	<script src="$homeModule.getTarget('/misc/js/ui-jp.config.js')"></script>
	<script src="$homeModule.getTarget('/misc/js/ui-jp.js')"></script>
	<script src="$homeModule.getTarget('/misc/js/ui-nav.js')"></script>
	<script src="$homeModule.getTarget('/misc/js/ui-toggle.js')"></script>
	<script src="$homeModule.getTarget('/misc/js/ui-tab.js')"></script>

</div>

