//团队精英-详情
var lawyerDetail=new Vue({
	el:"#layerDetailR",
	data:{
		obj:{},
		attachmentPath:''
	},
	mounted: function(){
		this.getData()
	},
	methods:{
		getData:function(){
			var myid = global.getQueryString('id');
			$.ajax({
				url:global.data.baseUrl+'/jzmb/portalNews/selectNewDetails',
				type: "post",
				data:{
					id:myid
				},
				success: function(data){
					lawyerDetail.obj=data.DATA;
					lawyerDetail.attachmentPath=data.attachmentPath;
				}
			})
		},
		check: function(){
			var id = e.currentTarget.id;
			$.ajax({
				url:global.data.baseUrl+'/jzmb/portalNews/selectNewsById',
				type:"post",
				data:{
					id:id
				},
				success:function(){
					lawyerDetail.obj=data.DATA;
				}
			});
		}
	}
})

//团队精英详情-侧边栏
var aside=new Vue({
	el:"#aside",
	data:{
		list:[],
		classifyName:'',
		ins:null
	},
	mounted: function(){
		this.getData()
	},
	methods:{
		getData:function(){
			var myid = global.getQueryString('classifyId');
			$.ajax({
				url:global.data.baseUrl+'/jzmb/portalNews/selectNewsById',
				type: "post",
				data:{
					id:myid,
					type:'top'
				},
				success:function(data){
					console.log(data);
					aside.list=data.DATA;
					aside.classifyName=data.DATA[0].classifyName;
				},
				error:function (msg) {
					alert(msg)
				}
			})
		},
		check:function (e) {
			var id = e.currentTarget.id;
			this.ins = parseInt($(e.currentTarget).attr("index"));
			
			$.ajax({
				url:global.data.baseUrl+'/jzmb/portalNews/selectNewDetails',
				type: "post",
				data:{
					id:id
				},
				success: function(data){
					lawyerDetail.obj=data.DATA;
				}
			})
		}
	}
})


