$(function(){
    $('#MainImageContent').on('mouseover','area',function(){
        var altText = $(this).attr('alt');
        $.ajax({
            type: 'GET',
            url:    'http://localhost:8080/PetStore_Web_exploded/autoTap?altText='+altText,
            success(data) {
                console.log(data);
                var autotap = '';
                autotap += '<li class="autotapitem">';
                autotap += data[0].categoryId;
                autotap += '</li>';
                for (var i = 0; i < data.length; i++) {
                    autotap += '<li class="autotapitem">';
                    autotap += data[i].name;
                    autotap += '</li>';
                    autotap += '<li class="autotapitem">';
                    autotap += data[i].productId;
                }

                $('#image-info').html(autotap);
                $('#image-info').show();
            },
            error(errorMsg){
                console.log(errorMsg);
            }
        })
    })
    $('#MainImageContent').on('mouseout','area',function(){
        $('#image-info').hide();
    })
})