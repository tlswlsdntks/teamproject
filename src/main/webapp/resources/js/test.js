function reply_list(rno, replyer, replyText, updateDate, userId) {
	
	var disabled = replyer==userId ? '' : 'disabled';
	
	var msg = `
	
		<div class="panel panel-primary">
			<div class="panel-heading">
				<span>${rno}</span>	
				<span><span class="glyphicon glyphicon-user" aria-hidden="true"></span>${replyer}</span>
				<span class="pull-right"><span class="glyphicon glyphicon-time" aria-hidden="true"></span>${updateDate}</span>
			</div>
			
			<div class="panel-body">
				<p>${replyText}</p>
				<div data-rno='${rno}' data-replyer='${replyer}'>
					<button class=" ${disabled} btn btn-warning btn-xs reply_btn_update_form" data-target="#myModal" data-toggle="modal">수정</button>
					<button class=" ${disabled} btn btn-warning btn-xs reply_btn_delete">삭제</button>
				</div>
			</div>
		</div>
		
	`;
	return msg;
}

function checkImageType(filename) {

	var idx = filename.lastIndexOf(".") + 1;
	var extendName = filename.substring(idx).toUpperCase();
	if (extendName == "JPG" || extendName == "JPEG" || extendName == "GIF" || extendName == "PNG")
		return true;
	else
		return false;
}

function getOriginalName(filename) {

	var idx = -1;
	if (checkImageType(filename))
		idx = filename.indexOf("_s_") + 3;
	else
		idx = filename.indexOf("_") + 1;
	return filename.substring(idx);
}

function getLinkFileName(filename) {

	var prefix = -1;
	var suffix = -1;
	if (checkImageType(filename)) {
		prefix = filename.substring(0, filename.indexOf("_s_"));
		suffix = filename.substring(filename.indexOf("_s_") + 2);
		filename = prefix + suffix;
	}
	return "/displayFile?filename=" + filename;
}

function uploadViewForm(getLinkFileName, filename, getOriginalName) {
	
	var src = '';
	
	if(checkImageType(filename))
		src = "/displayFile?filename=" + filename;
	else
		src = "/resources/img/esc.png";
	
	var msg = `
	<div class='col-xs-4' style='margin : 20px 0;'>
		<a target="_blank" href="${getLinkFileName}">
			<img src="${src}">
		</a>
		<p class="ellipsisTarget">${getOriginalName}</p>
	</div>
	`;
	
	return msg;
}

function uploadUpdateForm(getLinkFileName, filename, getOriginalName) {
	
	var src = '';
	
	if(checkImageType(filename))
		src = "/displayFile?filename=" + filename;
	else
		src = "/resources/img/esc.png";
	
	var msg = `
	<div class='col-xs-4'>
		<a target="_blank" href="${getLinkFileName}">
			<img src="${src}">
		</a>
		<p class="ellipsisTarget"><small class="delFile" data-filename="${filename}" style="border:1px solid red; cursor:pointer;">x</small>${getOriginalName}</p>
	</div>
	`;
	
	return msg;
}


function getFileUploadFileNameInput(index, filename){
	
	var msg = `
		<input name="files[${index}]" type="hidden" value="${filename}">
	`;
	
	return msg;
}


