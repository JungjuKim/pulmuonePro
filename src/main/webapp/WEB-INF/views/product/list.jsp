<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>풀무원 녹즙</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" type="image/x-icon"
	href="/resources/assets/images/pul_favicon.png">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
<script src="/resources/assets/js/jquery-2.1.4.min.js"></script>
<script src="/resources/assets/js/jquery.form.min.js"></script>
<link rel="stylesheet" href="/resources/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/assets/css/bootstrap-fdd.css">
<script src="/resources/assets/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="/resources/assets/js/clipboard.min.js"></script>
<script src="/resources/assets/js/fdd.js"></script>
<script src="/resources/assets/js/request.js"></script>
<link rel="stylesheet" href="/resources/assets/css/owl.carousel.min.css">
<link rel="stylesheet"
	href="/resources/assets/css/owl.theme.default.css">
<script src="/resources/assets/js/owl.carousel.min.js"></script>
<script src="/resources/assets/js/design.js"></script>
<link rel="stylesheet" href="/resources/assets/css/list.css">
<link rel="stylesheet" href="/resources/assets/css/style.css">
<script>            
      window.is_signed = true;
      window.kakaoSimpleData = {"memberId":"aaaaaaaa","name":"임재석","recommenderCode":"XQNGV"};                 
</script>
<script>
	window.dataLayer = window.dataLayer || [];

	function gtag() {
		dataLayer.push(arguments);
	}

	gtag('js', new Date());

	gtag('config', 'UA-150666346-1');

	var timer = undefined
</script>
</head>
<body>
	<script>
	$(function (){
		$('input[name=chk-prd1]').change(function () {
			const checked = $('input[name=chk-prd1]:checked')
			const checkedCnt = checked.length

			const total = $('input[name=chk-prd1]').length
			$('.prd-checkout-area .count span').text(checkedCnt)
			$('#chk-all').prop('checked', total === checkedCnt)

		})
		$('#chk-all').click(function () {
			$('input[name=chk-prd1]').prop('checked', $(this).is(':checked'))
			$('input[name=chk-prd1]').trigger('change');
		})
		$(document).on('click', '.btn-delete', function () {
			const idx = $(this).data('idx');
			const el = $(this)
			confirmDesign("","찜한상품을 삭제하시겠습니까?",function(){
			  $.post("/mypage/product/delete.do?idx="+idx,) 						
			  .done(function(data){
				  alert('목록에서 삭제되었습니다', () => location.reload())
			  })
			  .fail(function(data){
				  alert('잘못된 요청입니다.');
			  })
			})
			});		
		$(document).on("click", '#deleteBtn', function () {
			const checked = $('input[name=chk-prd1]:checked')
			if (checked.length === 0) {
				return alert('선택된 상품이 없습니다.');
			}
			const idxes = checked.map((i, v) => $(v).closest('li').data('idx')).toArray();
			confirmDesign("","찜한상품을 삭제하시겠습니까?",function(){
				  $.post("/mypage/product/delete.do?idx="+idxes,) 						
				  .done(function(data){
					  alert('목록에서 삭제되었습니다', () => location.reload())
				  })
				  .fail(function(data){
					  alert('잘못된 요청입니다.');
				  })
				})

		});
	});
	function hrefMove(_this) {
		let isSale = $(_this).data('issale');
		let url = $(_this).data('url');
		if (isSale == 'X') {
			alert("판매가 중지된 상품입니다.");
		} else if (isSale == 'N') {
			alert("판매가 일지 중단된 상품입니다.");
		} else {
			location.href = url
		}
	}

</script>
	<div class="wrapper">

		<%@ include file="/WEB-INF/views/layouts/header.jsp"%>
		<main class="page forum">
			<div class="breadcrumb-style">
				<div class="container">
					<ul>
						<li><a href="/">홈</a></li>
						<li><a href="/mypage.do">MY녹즙</a></li>
						<li><a class="" href="">활동정보</a></li>
						<li><a class="active" href="">찜한상품</a></li>
					</ul>
				</div>
			</div>
			<div class="container aside-layout" style="padding-bottom: 120px;">
				<%@ include file="/WEB-INF/views/layouts/mypage/aside.jsp"%>
				<script>
  $(document).on("click", "#mypage_lnb .indepth>a", function (e) {
    var parent = $(this).parents("li");
    if (parent.hasClass("active")) {
      parent.removeClass("active");
    } else {
      parent.addClass("active");
    }
    e.preventDefault();
    return false;
  });
  $(document).ready(function () {
    var item = undefined;
    $("#mypage_lnb .sub-navigation a").each(function (ix, elem) {
      var el = $(elem);
      if (location.pathname.startsWith(el.attr("href")) && !item) {
        item = el;
      }
    })
    if (!item) {
      $("#mypage_lnb .lnb-style>li>a").each(function (ix, elem) {
        var el = $(elem);
        if (el.attr("href") && location.pathname.startsWith(el.attr("href")) && !item) {
          item = el;
        }
      })
    }
    if (item) {
      item.parents("li").addClass("active");
      item.parents(".indepth").addClass("active");
    }
  })
</script>
				<div class="container">
					<div class="border-wrapper">
						<h2 class="container-title">찜한 상품</h2>
					</div>
					<div class="page-addiction-wrapper"
						style="align-items: center; margin-bottom: 17px;">
						<div class="info-copy description" style="margin-top: 7px;">
							<p style="padding: 0;">
								총 <b class="cnt">${fn:length(wishlist) }</b>건
							</p>
						</div>
					</div>
					<div class="box-partition"
						style="margin-bottom: 12px; padding: 0 20px; height: 60px; display: flex; justify-content: space-between; align-items: center">
						<label style="margin-bottom: 14px" class="check-type"> <input
							class="chk-all" id="chk-all" name="chk-all" type="checkbox">
							<span
							style="font-size: 16px; margin-bottom: 4px; padding-left: 51px;">전체선택</span>
						</label>
						<button type="button" id="deleteBtn" class="button-text">선택삭제</button>
					</div>
					<div class="box-partition" style="border: unset">

						<ul class="drinkchange-list favorite" id="pagable-items"
							data-list-object="append"
							style="border: 1px solid #e5e5e5; border-radius: 10px">
							<c:forEach items="${wishlist }" var="dto">
								<li data-idx="${dto.idx }"><label class="item-wrapper">
										<input name="chk-prd1" type="checkbox">
										<div class="item">
											<a
												data-url="/product/daily/view.do?tag=${dto.products_tag }&eventIdx="
												onclick="event.preventDefault();hrefMove(this)"
												data-issale="Y" style="display: flex">
												<div class="thumb">
													<img src="/file/download//product/${dto.system_name }"
														alt="">
												</div>
												<div class="contents">
													<p class="prd-title">${dto.products_name }</p>
													<b class="price">${dto.price }<span>
															원(${dto.products_size})</span>
													</b>
												</div>
											</a>
										</div>

										<button type="button" data-idx="${dto.idx }"
											class="btn-delete">
											<i class="ico ico-prd-delete"></i> <span class="hide">카트에서
												삭제</span>
										</button>
								</label></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</main>
		<%@ include file="/WEB-INF/views/layouts/footer.jsp"%>
		<div class="modal fade" id="alertModal" tabindex="-1"
			aria-labelledby="alertModalLabel"
			style="display: none; padding-right: 17px;" aria-modal="true"
			role="dialog">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="alertModalLabel"></h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">선택된 상품이 없습니다.</div>
					<button type="button" class="modal-footer" data-dismiss="modal">확인</button>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/ui/modal.jsp"%>
	<script>
	
	let currentCate = `${param.category}`;
	if( ${param.category == null || param.category == ""} ) currentCate = "전체";  
	$(".inquiry-cate-select .dropdown-toggle").text(currentCate);
	$(".inquiry-cate-select .dropdown-item").on("click", function(){
		let val = $(this).data("value");
		if( val != "" ) {
			location.href = `/mypage/inquiry/list.do?category=\${ val }`;		
		}else {
			location.href = "/mypage/inquiry/list.do";
			
		}
	})
</script>

</body>
</html>