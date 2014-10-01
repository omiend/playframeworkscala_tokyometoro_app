;(function ($) {

  // --------------------------------------------------------------------------------------------------------
  // --- TokyoMetoro RestAPI
  // --------------------------------------------------------------------------------------------------------
  /** 路線一覧取得処理 */
  getRailWay();

  /** 路線変更時の処理 */
  $.fn.changeRailWay = function() {
    $("#RailWay").change(function(){
      if (this.value) {
        // 列車ロケーション情報取得
        getTrain(this.value);
      }
    });
  }

})(jQuery);

/** 鉄道路線情報 odpt:Railway */
function getRailWay() {
  jsRoutes.controllers.AjaxController.getRailway("odpt:Railway").ajax({
    beforeSend: function() {},
    complete: function() {},
    success: function(result) {
      $("#RailWay").append($('<option>').html("--- 選択してください ---").val(""));
      var resultList = new Backbone.Collection(JSON.parse(result));
      resultList.each(function(item, index){
        if (item.get("owl_sameAs") == "odpt.Railway_TokyoMetro.MarunouchiBranch") {
          $("#RailWay").append($('<option>').html(item.get("dc_title") + "線（分岐線）").val(item.get("owl_sameAs")));
        } else {
          $("#RailWay").append($('<option>').html(item.get("dc_title") + "線").val(item.get("owl_sameAs")));
        }
      });
    },
    error: function(result) {
      window.alert("エラーが発生しました。画面を更新して頂くか、時間をおいてから再度お試し下さい。");
      console.log("error : " + result)
    }
  });
}

/** 列車ロケーション情報 odpt:Train */
function getTrain(railWay) {
  jsRoutes.controllers.AjaxController.getTrain(railWay).ajax({
    beforeSend: function() {},
    complete: function() {},
    success: function(result) {
      var resultList = new Backbone.Collection(JSON.parse(result));
      resultList.each(function(item, index){
        console.log(item);
        console.log(item.get("odpt_fromStation") + " → " + item.get("odpt_toStation"));
      });
    },
    error: function(result) {
      window.alert("エラーが発生しました。画面を更新して頂くか、時間をおいてから再度お試し下さい。");
      console.log("error : " + result)
    }
  });
 }

// // Some general UI pack related JS
// // Extend JS String with repeat method
// String.prototype.repeat = function (num) {
//   return new Array(num + 1).join(this);
// };

// (function ($) {

//   // Add segments to a slider
//   $.fn.addSliderSegments = function (amount, orientation) {
//     return this.each(function () {
//       if (orientation === 'vertical') {
//         var output = '';
//         var i;
//         for (i = 1; i <= amount - 2; i++) {
//           output += '<div class="ui-slider-segment" style="top:' + 100 / (amount - 1) * i + '%;"></div>';
//         }
//         $(this).prepend(output);
//       } else {
//         var segmentGap = 100 / (amount - 1) + '%';
//         var segment = '<div class="ui-slider-segment" style="margin-left: ' + segmentGap + ';"></div>';
//         $(this).prepend(segment.repeat(amount - 2));
//       }
//     });
//   };

//   $(function () {

//     // Todo list
//     $('.todo').on('click', 'li', function () {
//       $(this).toggleClass('todo-done');
//     });

//     // Custom Selects
//     if ($('[data-toggle="select"]').length) {
//       $('[data-toggle="select"]').select2();
//     }

//     // Checkboxes and Radio buttons
//     $('[data-toggle="checkbox"]').radiocheck();
//     $('[data-toggle="radio"]').radiocheck();

//     // Tooltips
//     $('[data-toggle=tooltip]').tooltip('show');

//     // jQuery UI Sliders
//     var $slider = $('#slider');
//     if ($slider.length > 0) {
//       $slider.slider({
//         min: 1,
//         max: 5,
//         value: 3,
//         orientation: 'horizontal',
//         range: 'min'
//       }).addSliderSegments($slider.slider('option').max);
//     }

//     var $verticalSlider = $('#vertical-slider');
//     if ($verticalSlider.length) {
//       $verticalSlider.slider({
//         min: 1,
//         max: 5,
//         value: 3,
//         orientation: 'vertical',
//         range: 'min'
//       }).addSliderSegments($verticalSlider.slider('option').max, 'vertical');
//     }

//     // Focus state for append/prepend inputs
//     $('.input-group').on('focus', '.form-control', function () {
//       $(this).closest('.input-group, .form-group').addClass('focus');
//     }).on('blur', '.form-control', function () {
//       $(this).closest('.input-group, .form-group').removeClass('focus');
//     });

//     // Make pagination demo work
//     $('.pagination').on('click', 'a', function () {
//       $(this).parent().siblings('li').removeClass('active').end().addClass('active');
//     });

//     $('.btn-group').on('click', 'a', function () {
//       $(this).siblings().removeClass('active').end().addClass('active');
//     });

//     // Disable link clicks to prevent page scrolling
//     $(document).on('click', 'a[href="#fakelink"]', function (e) {
//       e.preventDefault();
//     });

//     // Switches
//     if ($('[data-toggle="switch"]').length) {
//       $('[data-toggle="switch"]').bootstrapSwitch();
//     }

//     // Typeahead
//     if ($('#typeahead-demo-01').length) {
//       var states = new Bloodhound({
//         datumTokenizer: function (d) { return Bloodhound.tokenizers.whitespace(d.word); },
//         queryTokenizer: Bloodhound.tokenizers.whitespace,
//         limit: 4,
//         local: [
//           { word: 'Alabama' },
//           { word: 'Alaska' },
//           { word: 'Arizona' },
//           { word: 'Arkansas' },
//           { word: 'California' },
//           { word: 'Colorado' }
//         ]
//       });

//       states.initialize();

//       $('#typeahead-demo-01').typeahead(null, {
//         name: 'states',
//         displayKey: 'word',
//         source: states.ttAdapter()
//       });
//     }

//     // make code pretty
//     window.prettyPrint && prettyPrint();

//   });

// })(jQuery);
