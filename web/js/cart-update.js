
    $(function () {
        $('.quantity').on('keyup', function () {
            var quantity = $(this).val();
            console.log(quantity);
            if (quantity !== '' && !quantity !== null && quantity.length !== 0) {
                $.ajax({
                    type: 'GET',
                    url: 'http://localhost:8080/PetStore_Web_exploded/updateCartQuantities',
                    data: {'quantity': quantity},
                    success: function (data) {
                        console.log(data);
                        $('#update').click();
                    },
                    error: function (errorMsg) {
                        console.log(errorMsg);
                    }
                })
            }
        })
    })
