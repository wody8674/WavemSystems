/*
 * Droppy 0.1.2
 * (c) 2008 Jason Frame (jason@onehackoranother.com)
 */

/*
 * Droppy 0.1.2
 * (c) 2008 Jason Frame (jason@onehackoranother.com)
 */
(function($) {
  
  $.fn.droppy = function(options) {

    options = $.extend({speed: 250, className: 'droppy', trigger: 'hover'}, options || {});

    this.each(function() {

      var root = this, zIndex = 1000;

      $(root).addClass(options.className);
			$(root).find('li:has(> ul) > a').addClass('has-subnav');

      function getSubnav(ele) {
        if (ele.nodeName.toLowerCase() == 'li') {
          var subnav = $('> ul', ele);
          return subnav.length ? subnav[0] : null;
        } else {
          return ele;
        }
      };

      function getActuator(ele) {
        if (ele.nodeName.toLowerCase() == 'ul') {
          return $(ele).parents('li')[0];
        } else {
          return ele;
        }
      };

      function hide() {
        var subnav = getSubnav(this);
        if (!subnav) return;
        $.data(subnav, 'cancelHide', false);
        setTimeout(function() {
          if (!$.data(subnav, 'cancelHide')) {
            $(subnav).slideUp(options.speed);
          }
        }, 500);
      };

      function show() {
        var subnav = getSubnav(this);
        if (!subnav) return;
        $.data(subnav, 'cancelHide', true);
        $(subnav).css({zIndex: zIndex++}).slideDown(options.speed);
        if (this.nodeName.toLowerCase() == 'ul') {
          var li = getActuator(this);
          $(li).addClass('hover');
          $('> a', li).addClass('hover');
        }
        return false;
      };
      
      if (options.trigger == 'click') {
        $('> li', this).click(show);
        $('> li ul, > li li', this).hover(show, function() {});
        $('ul, li', this).hover(function() {}, hide);
      } else {
        if (typeof $.fn.hoverIntent == 'function') {
          $('ul, li', this).hoverIntent($.extend({
            sensitivity: 2, interval: 50, timeout: 100
          }, options.hoverIntent || {}, {over: show, out: hide}));
        } else {
          $('ul, li', this).hover(show, hide);
        }
      }
      
      $('li', this).hover(
        function() { $(this).addClass('hover'); $('> a', this).addClass('hover'); },
        function() { $(this).removeClass('hover'); $('> a', this).removeClass('hover'); }
      );

    });

  };

})(jQuery);



/*
(function($) {

jQuery(function($) {

$.fn.droppy = function(options) {
    
  options = $.extend({speed: 200}, options || {});
  
  this.each(function() {
    
    var root = this, zIndex = 1000;
    
    function getSubnav(ele) {
      if (ele.nodeName.toLowerCase() == 'li') {
        var subnav = $('> ul', ele);
        return subnav.length ? subnav[0] : null;
      } else {
        return ele;
      }
    }
    
    function getActuator(ele) {
      if (ele.nodeName.toLowerCase() == 'ul') {
        return $(ele).parents('li')[0];
      } else {
        return ele;
      }
    }
    
    function hide() {
      var subnav = getSubnav(this);
      if (!subnav) return;
      $.data(subnav, 'cancelHide', false);
      setTimeout(function() {
        if (!$.data(subnav, 'cancelHide')) {
          $(subnav).fadeOut(options.speed);
        }
      }, 500);
    }
  
    function show() {
      var subnav = getSubnav(this);
      if (!subnav) return;
      $.data(subnav, 'cancelHide', true);
      $(subnav).css({zIndex: zIndex++}).slideDown(options.speed);
      if (this.nodeName.toLowerCase() == 'ul') {
        var li = getActuator(this);
        $(li).addClass('hover');
        $('> a', li).addClass('hover');
      }
    }
    
    $('ul, li', this).hover(show, hide);
    $('li', this).hover(
      function() { $(this).addClass('hover'); $('> a', this).addClass('hover'); },
      function() { $(this).removeClass('hover'); $('> a', this).removeClass('hover'); }
    );
    
  });
  
};
});

}) (jQuery);
*/


