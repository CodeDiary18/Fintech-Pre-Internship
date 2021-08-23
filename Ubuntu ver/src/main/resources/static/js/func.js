function setDisplay() {
    if ($('input:radio[id=af]').is(':checked')) { //개인
        $('#agencyF').show();
        $('#agencyT').hide();
    } else {
        $('#agencyF').hide();
        $('#agencyT').show();
    }
}