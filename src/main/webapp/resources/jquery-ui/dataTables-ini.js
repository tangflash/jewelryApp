function iniDataTable() {
	var oTable= $(".listTable").dataTable({
		"sPaginationType" : "full_numbers",
		"bJQueryUI" : true,
		"bProcessing" : true,
		"iDisplayLength": 50,
		"bLengthChange": true,
		"aaSorting" : [],
		"oLanguage" : {
			"sLengthMenu" : "每页显示 _MENU_ 行",
			"sZeroRecords" : "没有记录",
			"sInfo" : "当前显示 _START_ ~ _END_ 条，共 _TOTAL_ 条记录",
			"sInfoEmpty" : "当前显示 0~0 条，共 0 条记录",
			"sInfoFiltered" : "(从 _MAX_ 条记录中搜索)",
			"sLoadingRecords" : "正在加载数据...",
			"sProcessing" : "正在处理数据...",
			"sSearch" : "搜索:",			
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "上一页",
				"sNext" : "下一页",
				"sLast" : "尾页"
			}
		},
	});
	
	$(".listTable tbody tr").click( function( e ) {
        if ( $(this).hasClass('row_selected') ) {
            $(this).removeClass('row_selected');
        }
        else {
            oTable.$('tr.row_selected').removeClass('row_selected');
            $(this).addClass('row_selected');
        }
    });
}