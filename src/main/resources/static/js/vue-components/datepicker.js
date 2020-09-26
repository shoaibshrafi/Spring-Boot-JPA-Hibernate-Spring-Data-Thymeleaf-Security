var datepickerComponent = Vue.extend({
  //v-el:select
  template: '<div class="input-group date" v-el:inputgroup>' +
    '<input type="text" class="form-control" v-model="value"><span class="input-group-addon"><i class="fas fa-calendar-alt"></i></span>' +
    '</div>',
  props: {
    value: '2015-01-01'
  },
  data: function() {
    return {};
  },
  created: function() {
	  alert('tst')
    $(this.$els.inputgroup).datetimepicker({
      format: 'yyyy/mm/dd',
      autoclose: true
    });
  }
});

Vue.component('datepicker', datepickerComponent);