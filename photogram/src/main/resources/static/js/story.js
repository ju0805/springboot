/**
	2. 스토리 페이지
	(1) 스토리 로드하기
	(2) 스토리 스크롤 페이징하기
	(3) 좋아요, 안좋아요
	(4) 댓글쓰기
	(5) 댓글삭제
 */

let principalId = $("#principalId").val();

// (1) 스토리 로드하기
let page = 0

function storyLoad() {
	$.ajax({
		url: `/api/image?page=${page}`,
		dataType: "json"
	}).done(res=>{
		console.log(res);
		res.data.content.forEach((img)=>{
			let items = getStoryItem(img);
			$("#storyList").append(items);
		})
	}).fail(error=>{
		console.log(error);
	})
}

storyLoad();

function getStoryItem(img) {
	let item = `
		<div class="story-list__item">
			<div class="sl__item__header">
				<div>
					<img class="profile-image" src="/upload/${img.user.profileImageUrl}"
						onerror="this.src='/images/person.jpeg'" />
				</div>
				<div>${img.user.username}</div>
			</div>
		
			<div class="sl__item__img">
				<img src="/upload/${img.postImageUrl}" />
			</div>
		
			<div class="sl__item__contents">
				<div class="sl__item__contents__icon">
		
					<button>`;
					
						if(img.likeState){
							item += `<i class="fas fa-heart active" id="storyLikeIcon-${img.id}" onclick="toggleLike(${img.id})"></i>`;
						}else{
							item += `<i class="fa-heart far" id="storyLikeIcon-${img.id}" onclick="toggleLike(${img.id})"></i>`;
						}
						
						
					item +=`
					</button>
				</div>
		
				<span class="like"><b id="storyLikeCount-${img.id}">${img.likeCount} </b>likes</span>
		
				<div class="sl__item__contents__content">
					<p>${img.caption}</p>
				</div>
		
				<div id="storyCommentList-${img.id}">`;
					
					img.comments.forEach((comment)=>{
						item +=`<div class="sl__item__contents__comment" id="storyCommentItem-${comment.id}">
								<p>
									<b>${comment.user.username} :</b> ${comment.content}
								</p>`;
				
								if(principalId == comment.user.id){
									item += `	<button onclick="deleteComment(${comment.id})">
														<i class="fas fa-times"></i>
													</button>`;
								}
								
							item += `	
							</div>`;
					});
				
		
				item += `
				</div>
		
				<div class="sl__item__input">
					<input type="text" placeholder="댓글 달기" id="storyCommentInput-${img.id}" />
					<button type="button" onClick="addComment(${img.id})">게시</button>
				</div>
		
			</div>
		</div>`
	
	return item;
}

// (2) 스토리 스크롤 페이징하기
$(window).scroll(() => {
	let checkNum = $(window).scrollTop() - ($(document).height() -  $(window).height());
	
	if(checkNum < 1 && checkNum > -1){
		page ++;
		storyLoad();
	}
});


// (3) 좋아요, 안좋아요
function toggleLike(imageId) {
	let likeIcon = $(`#storyLikeIcon-${imageId}`);
	
	if (likeIcon.hasClass("far")) {
		
		$.ajax({
			
			type:"post",
			url:`/api/image/${imageId}/likes`,
			dataType:"JSON"
			
		}).done(res=>{
			
			let likeCountStr = $(`#storyLikeCount-${imageId}`).text();
			let likeCount = Number(likeCountStr) + 1;
			$(`#storyLikeCount-${imageId}`).text(likeCount);
			
			likeIcon.addClass("fas");
			likeIcon.addClass("active");
			likeIcon.removeClass("far");
			
		}).fail(error=>{
			console.log(error);
		});
	
	} else {
		
		$.ajax({
			
			type:"delete",
			url:`/api/image/${imageId}/likes`,
			dataType:"JSON"
			
		}).done(res=>{
			
			let likeCountStr = $(`#storyLikeCount-${imageId}`).text();
			let likeCount = Number(likeCountStr) - 1;
			$(`#storyLikeCount-${imageId}`).text(likeCount);
			
			likeIcon.removeClass("fas");
			likeIcon.removeClass("active");
			likeIcon.addClass("far");
			
		}).fail(error=>{
			console.log(error);
		});
		
	}
}

// (4) 댓글쓰기
function addComment(imageId) {

	let commentInput = $(`#storyCommentInput-${imageId}`);
	let commentList = $(`#storyCommentList-${imageId}`);

	let data = {
		imageId: imageId,
		content: commentInput.val()
	}

	if (data.content === "") {
		alert("댓글을 작성해주세요!");
		return;
	}

	$.ajax({
		type: "post",
		url: "/api/comment",
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json"	
	}).done(res=>{
		let comment = res.data;
		
		let content = `
				  <div class="sl__item__contents__comment" id="storyCommentItem-${comment.id}">
				    <p>
				      <b>${comment.user.username} :</b>
				       ${comment.content}
				    </p>
				    <button onclick="deleteComment(${comment.id})"><i class="fas fa-times"></i></button>
				  </div>
				`;
		commentList.prepend(content);
	}).fail(error=>{
		console.log("오류", error.responseJSON.data.content);
		alert(error.responseJSON.data.content);
	})

	commentInput.val("");
}

// (5) 댓글 삭제
function deleteComment(commentId) {
	
	$.ajax({
		type: "delete",
		url: `/api/comment/${commentId}`,
		dataType: "json"	
	}).done(res=>{
		$(`#storyCommentItem-${commentId}`).remove();
	}).fail(error=>{
		console.log("오류",error);
	});

}







