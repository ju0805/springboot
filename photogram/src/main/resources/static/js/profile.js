/**
  1. 유저 프로파일 페이지
  (1) 유저 프로파일 페이지 구독하기, 구독취소
  (2) 구독자 정보 모달 보기
  (3) 구독자 정보 모달에서 구독하기, 구독취소
  (4) 유저 프로필 사진 변경
  (5) 사용자 정보 메뉴 열기 닫기
  (6) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
  (7) 사용자 프로파일 이미지 메뉴(사진업로드, 취소) 모달 
  (8) 구독자 정보 모달 닫기
 */

// (1) 유저 프로파일 페이지 구독하기, 구독취소
function toggleSubscribe(toUserId, obj) {
	if ($(obj).text() === "구독취소") {
		$.ajax({
			type:"delete",
			url: "/api/subscribe/"+toUserId,
			dataType:"JSON"
		}).done(res=>{
			$(obj).text("구독하기");
			$(obj).toggleClass("blue");
		}).fail(error=>{
			console.log("구독취소 실패 ", error)
		})
		
	} else {
		$.ajax({
			type:"post",
			url: "/api/subscribe/"+toUserId,
			dataType:"JSON"
		}).done(res=>{
			$(obj).text("구독취소");
			$(obj).toggleClass("blue");
		}).fail(error=>{
			console.log("구독하기 실패 ", error);
		})
	}
}

// (2) 구독자 정보  모달 보기
function subscribeInfoModalOpen(pageUserId) {
	$(".modal-subscribe").css("display", "flex");
	
	$.ajax({
		url: `/api/user/${pageUserId}/subscribe`,
		dataType:"JSON"
	}).done(res=>{
		console.log(res.data)
		res.data.forEach((u)=>{
			let item = getSubscribeModalItem(u);
			$("#subscribeModalList").append(item);
		});
	}).fail(error=>{
		console.log("구독정보 불러오기 오류",error);
	})
}

function getSubscribeModalItem(u) {
	let item = `
		<div class="subscribe__item" id="subscribeModalItem-${u.id}">
			<div class="subscribe__img">
				<img src="/upload/${u.profileImageUrl}" onerror="this.src='/images/person.jpeg'"/>
			</div>
			<div class="subscribe__text">
				<h2>${u.userName}</h2>
			</div>
			<div class="subscribe__btn">`;
				
				if(!u.equalUserState){
					if(u.subScribeState){
						item += `<button class="cta blue" onclick="toggleSubscribe(${u.id},this)">구독취소</button>`;
					}else{
						item += `<button class="cta" onclick="toggleSubscribe(${u.id},this)">구독하기</button>`;
					}
				}
			item += `	
			</div>
		</div>`
		
	return item;
}


// (3) 유저 프로파일 사진 변경 (완)
function profileImageUpload(pageUserId, principalId) {
	
	if(pageUserId != principalId){
		alert("해당 프로필 사진을 수정할 수 없습니다.");
		return;
	}
	

	$("#userProfileImageInput").click();

	$("#userProfileImageInput").on("change", (e) => {
		let f = e.target.files[0];

		if (!f.type.match("image.*")) {
			alert("이미지를 등록해야 합니다.");
			return;
		}
		
		let profileImageForm = $("#userProfileImageForm")[0];
		let formData = new FormData(profileImageForm);
		
		$.ajax({
			type: "put",
			url: `/api/user/${principalId}/profileImageUrl`,
			data: formData,
			contentType: false,
			processData: false, 
			enctype: "multipart/form-data",
			dataType: "json"
		}).done(res=>{
			let reader = new FileReader();
			reader.onload = (e) => {
				$("#userProfileImage").attr("src", e.target.result);
			}
			reader.readAsDataURL(f);
		}).fail(error=>{
			console.log("오류", error);
		})
	});
}


// (4) 사용자 정보 메뉴 열기 닫기
function popup(obj) {
	$(obj).css("display", "flex");
}

function closePopup(obj) {
	$(obj).css("display", "none");
}


// (5) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
function modalInfo() {
	$(".modal-info").css("display", "none");
}

// (6) 사용자 프로파일 이미지 메뉴(사진업로드, 취소) 모달
function modalImage() {
	$(".modal-image").css("display", "none");
}

// (7) 구독자 정보 모달 닫기
function modalClose() {
	$(".modal-subscribe").css("display", "none");
	location.reload();
}






