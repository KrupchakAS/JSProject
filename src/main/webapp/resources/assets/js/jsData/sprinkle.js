// update --------------------------------------

function getUpdateForm(sprinkleId, selector) {
    if (isNumber(sprinkleId)) {
        getSprinkleById(sprinkleId, selector);
    } else {
        alert("Sprinkle ID is not number");
    }
}

function isNumber(value) {
    return (value !== undefined && value !== null && value > 0);
}

function getSprinkleById(id, selector) {
    var ajax = {};
    ajax.data = {id: id};
    ajax.type = "GET";
    ajax.url = "/jsDonut/admin/sprinkle/getSprinkleById";
    ajax.dataType = 'JSON';
    ajax.selector = selector;
    ajax.successFunction = openSprinkleFormUpdate;
    sendAjax(ajax);
}

function openSprinkleFormUpdate(sprinkleObject) {

    $('.sprinkle-id-up').val(sprinkleObject.id);
    $('.sprinkle-name-up').val(sprinkleObject.name);
    $('.sprinkle-calories-up').val(sprinkleObject.calories);
    $('.sprinkle-price-up').val(sprinkleObject.price);

    $('.container-head').text("Sprinkle: " + sprinkleObject.name);
    $('.sprinkle-add').addClass('block__display-none');
    $('.sprinkle-list').addClass('block__display-none');
    $('.sprinkle-form-create').addClass('block__display-none');
    $('.sprinkle-form-update').removeClass('block__display-none');
}

function updateItem(button) {

    var pst = {};
    pst.selector = button;
    pst.type = "POST";
    pst.url = '/jsDonut/admin/sprinkle/updateSprinkle';
    pst.data = {};
    pst.data = getItemData();

    console.log(pst.data);

    sendAjax(pst);
}

function getItemData() {
    var sprinkle = {};

    sprinkle.id = parseInt($('.sprinkle-id-up').val());
    sprinkle.name = $('.sprinkle-name-up').val();
    sprinkle.price = parseFloat($('.sprinkle-price-up').val());
    sprinkle.calories = parseInt($('.sprinkle-calories-up').val());

    return sprinkle;
}


$(document).ready(function () {
    $(document).on('click', '.sprinkle-update', function (e) {
        e.preventDefault();
        updateItem($(this));
    });
});

// save -----------------------------


function saveItem(button) {

    var pst = {};
    pst.selector = button;
    pst.type = "POST";
    pst.url = '/jsDonut/admin/sprinkle/createSprinkle';
    pst.data = {};
    pst.data = getFormCreate();

    console.log(pst.data);

    sendAjax(pst);
}

function getFormCreate() {
    var sprinkle = {};

    sprinkle.name = $('.sprinkle-name-cr').val();
    sprinkle.price = parseFloat($('.sprinkle-price-cr').val());
    sprinkle.calories = parseInt($('.sprinkle-calories-cr').val());

    $('.sprinkle-add').addClass('block__display-none');
    $('.sprinkle-list').addClass('block__display-none');
    $('.sprinkle-form-update').addClass('block__display-none');
    $('.sprinkle-form-create').removeClass('block__display-none');

    return sprinkle;
}


$(document).ready(function () {
    $(document).on('click', '.sprinkle-save', function (e) {
        e.preventDefault();
        saveItem($(this));
    });
});

// Scripts

$(function() {
    $(document).on('click', '.sprinkle-add', function () {
        getFormCreate();
    });
    $(document).on('click', '.sprinkle-edit', function() {
        var id = $(this).parent().parent().data('id');
        getUpdateForm(id, $(this));
    });
    $(document).on('click', '.sprinkle-close', function() {
        closeSprinkle();
    });
    $(document).on('click', '.sprinkle-update', function() {
        closeSprinkle();
    });
    $(document).on('click', '.sprinkle-save', function() {
        closeSprinkle();
    });
});

function closeSprinkle() {

    $('.container-head').text("Sprinkle list");
    $('.sprinkle-list').removeClass('block__display-none');
    $('.sprinkle-add').removeClass('block__display-none');
    $('.sprinkle-form-update').addClass('block__display-none');
    $('.sprinkle-form-create').addClass('block__display-none');
}