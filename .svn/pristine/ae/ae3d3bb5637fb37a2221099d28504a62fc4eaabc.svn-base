//团队精英-列表
var lawyer = new Vue({
	el: "#lawyer-list",
	data: {
		list:[],
		attachmentPath:null,
		listName:'团队精英',
		
		numList:[],
        cur: 1,//当前页码,
        pageCount:0//总页数,
	},
	mounted: function(){
		this.getData();
	},
	methods: {
		getData: function(){
    		var that = this;
			$.ajax({
				type: "post",
				url: global.data.baseUrl+'/jzmb/portalNews/selectNewsByPermission',
				data: {
					classifyPermission: "team_crew",
					type: "top",
					pageNum:'1',
					pageSize:'9'
				},
				success: function(data){
					console.log(data);
					that.list=data.DATA;
					that.attachmentPath=data.attachmentPath;
					
					that.getPageNum(data.page);
				}
			})
		},
		enter: function(e){
			$(e.currentTarget).find(".lawyer-masktext").show()
		}, 
		leave: function(e){
			$(e.currentTarget).find(".lawyer-masktext").hide()
		},
    	//分页
    	getPageNum:function (obj) {
			this.pageCount = obj.pageCount;
			for (var i=0;i<this.pageCount;i++) {
				this.numList[i]=i+1;
			}
		},
		checkPage:function (e) {
    		var that = this;
			var $tar = $(e.currentTarget);
			that.cur = $(e.currentTarget).text();
    		
    		$tar.addClass("active").siblings("a").removeClass("active");
    		
    		$.ajax({
				type:"post",
				url:global.data.baseUrl+'/jzmb/portalNews/selectNewsByPermission',
				data:{
					classifyPermission:'team_crew',
					type:'top',
					pageNum:that.cur,
					pageSize:'9'
				},
				success:function(data){
					that.list=data.DATA;
					that.getPageNum(data.page);
				}
			});
		},
		prevPage:function () {
    		var that = this;
			that.cur--;
			$.ajax({
				type:"post",
				url:global.data.baseUrl+'/jzmb/portalNews/selectNewsByPermission',
				data:{
					classifyPermission:'team_crew',
					type:'top',
					pageNum:that.cur,
					pageSize:'9'
				},
				success:function(data){
					that.list=data.DATA;
					that.getPageNum(data.page);
				}
			});
		},
		nextPage:function () {
    		var that = this;
			that.cur++;
			$.ajax({
				type:"post",
				url:global.data.baseUrl+'/jzmb/portalNews/selectNewsByPermission',
				data:{
					classifyPermission:'team_crew',
					type:'top',
					pageNum:that.cur,
					pageSize:'9'
				},
				success:function(data){
					that.list=data.DATA;
					that.getPageNum(data.page);
				}
			});
		}
	}	
})

//团队精英-侧边栏
var lawyerS = new Vue({
	el: "#lawyerSidebar",
	data: {
		list:null,
		ins:null
	},
	mounted: function(){
		this.getData()
	},
	methods:{
		getData: function(){
			$.ajax({
				url: global.data.baseUrl+'/jzmb/portalNews/selectChildrenById',
				type: "post",
				data:{
					id: "1"
				},
				success: function(data){
					lawyerS.list = data.DATA
				}
			})
		},
		check:function (e) {
//			var $curObj = $(e.currentTarget); //获取当前元素
//			$(".lawyer-sidebar-ul li").click(function(){
//				$(this).addClass(".active").siblings().removeClass(".active");
//			});
//			$curObj.addClass(".active").siblings().removeClass(".active");
			this.ins = parseInt($(e.currentTarget).attr("index"));
			var id=e.currentTarget.id;
			var name =$(e.currentTarget).text();
			lawyer.listName=name;
			
			
			$.ajax({
				type: "post",
				url: global.data.baseUrl+'/jzmb/portalNews/selectNewsById',
				data: {
					id:id,
					type: "top"
				},
				success: function(data){
					lawyer.list=data.DATA;
				}
			})
			
		}
	}
})
