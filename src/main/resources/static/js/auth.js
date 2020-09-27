let index = {
    init:function() {
        $("#btn-join").on("click",()=>{
            this.join();
        });
        $("#btn-write").on("click",()=>{
            this.write();
        });
        $("#btn-delete").on("click",()=>{
            this.delete();
        });
        $("#btn-update").on("click",()=>{
            this.update();
        });
        $("#btn-reply-write").on("click",()=>{
            this.replyWrite();
        });
    },

    join:function() {

        let data = {
            username : $("#username").val(),
            password : $("#password").val(),
            name : $("#name").val()
        }

        $.ajax({
            type:"post",
            url:"/auth/joinPost",
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
            alert("회원가입이 완료되었습니다.");
            console.log(resp);
            location.href="/";
        }).fail(function(err){
            alert(JSON.stringify(err));
        });
    },

    write:function() {

        let data = {
            title : $("#title").val(),
            content : $("#content").val()
        }

        $.ajax({
            type:"post",
            url:"/api/board",
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
            alert("글작성이 완료되었습니다.");
            location.href="/board/list";
        }).fail(function(err){
            alert(JSON.stringify(err));
        });
    },

    delete:function(){

        let a = confirm("정말 삭제하시겠습니까?")

        if(!a) {
            return false;
        }

        let id = $("#id").val();

        $.ajax({
            type:"delete",
            url:"/api/board/"+id
        }).done(function(resp){
            alert("삭제가 완료되었습니다.");
            location.href="/board/list";
        }).fail(function(err){
            alert(JSON.stringify(err));
        });
    },

    update:function() {

        let id = $("#id").val();

        let data = {
            title : $("#title").val(),
            content : $("#content").val()
        };

        $.ajax({
            type:"put",
            url:"/api/board/"+id,
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8"
        }).done(function(resp){
            alert("수정이 완료되었습니다");
            location.href="/board/list";
        }).fail(function(err){
            alert(JSON.stringify(err));
        });
    },

    replyWrite:function() {

        let id = $("#id").val();

        let data = {
            content : $("#reply-content").val()
        };

        $.ajax({
            type:"post",
            url:`/api/board/${id}/reply`,
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8"
        }).done(function(resp) {
            location.href=`/board/${id}`;
        }).fail(function(err){
            alert(JSON.stringify(err));
        });
    }
}

index.init();
