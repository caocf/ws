$(function(){
  
  function pageselectCallback(page_index, jq, pre_name) {
    var localurl = window.location.toString();
    pre_name = pre_name.length > 0 ? (pre_name + '.page') : 'page';
    var newlocal = new Uri(localurl).replaceQueryParam(pre_name, page_index + 1).toString();
    window.location = newlocal;
    return false;
  }

  $('.pagination').each(function(){
    
    var settings = $(this).attr('title').split(/,/);
    //settings[0] total records
    //settings[1] current page
    //settings[2] items per page
    var pre_name = '';
    if (settings.length > 3) {
      pre_name = settings[3];
    }
    
    $(this).attr('title', '共' + settings[0] + '条记录，当前第' + (new Number(settings[1])) + '页，每页最大' + settings[2] + '条记录');
    
    $(this).pagination(settings[0], {
      current_page: settings[1] - 1,
      items_per_page: settings[2],
      num_display_entries: 4,
      num_edge_entries: 2,
      prev_text:"<",
      next_text:">",
      pre_name:pre_name,
      callback: pageselectCallback
    });
    
    
  });
  
  
});





/**
 * This jQuery plugin displays pagination links inside the selected elements.
 * 
 * This plugin needs at least jQuery 1.4.2
 *
 * @author Gabriel Birke (birke *at* d-scribe *dot* de)
 * @version 2.2
 * @param {int} maxentries Number of entries to paginate
 * @param {Object} opts Several options (see README for documentation)
 * @return {Object} jQuery Object
 */
 (function($){
  /**
   * @class Class for calculating pagination values
   */
  $.PaginationCalculator = function(maxentries, opts) {
    this.maxentries = maxentries;
    this.opts = opts;
  }
  
  $.extend($.PaginationCalculator.prototype, {
    /**
     * Calculate the maximum number of pages
     * @method
     * @returns {Number}
     */
    numPages:function() {
      return Math.ceil(this.maxentries/this.opts.items_per_page);
    },
    /**
     * Calculate start and end point of pagination links depending on 
     * current_page and num_display_entries.
     * @returns {Array}
     */
    getInterval:function(current_page)  {
      var ne_half = Math.floor(this.opts.num_display_entries/2);
      var np = this.numPages();
      var upper_limit = np - this.opts.num_display_entries;
      var start = current_page > ne_half ? Math.max( Math.min(current_page - ne_half, upper_limit), 0 ) : 0;
      var end = current_page > ne_half?Math.min(current_page+ne_half + (this.opts.num_display_entries % 2), np):Math.min(this.opts.num_display_entries, np);
      return {start:start, end:end};
    }
  });
  
  // Initialize jQuery object container for pagination renderers
  $.PaginationRenderers = {}
  
  /**
   * @class Default renderer for rendering pagination links
   */
  $.PaginationRenderers.defaultRenderer = function(maxentries, opts) {
    this.maxentries = maxentries;
    this.opts = opts;
    this.pc = new $.PaginationCalculator(maxentries, opts);
  }
  $.extend($.PaginationRenderers.defaultRenderer.prototype, {
    /**
     * Helper function for generating a single link (or a span tag if it's the current page)
     * @param {Number} page_id The page id for the new item
     * @param {Number} current_page 
     * @param {Object} appendopts Options for the new item: text and classes
     * @returns {jQuery} jQuery object containing the link
     */
    createLink:function(page_id, current_page, appendopts){
      var lnk, np = this.pc.numPages();
      page_id = page_id<0?0:(page_id<np?page_id:np-1); // Normalize page id to sane value
      appendopts = $.extend({text:page_id+1, classes:""}, appendopts||{});
      if(page_id == current_page){
      //  lnk = $("<span class='current'>" + appendopts.text + "</span>");
          lnk = $("<li><span>" + appendopts.text + "</span></li>");
      }
      else
      {
          lnk = $("<li><a>" + appendopts.text + "</a></li>");
          //lnk = $("<a>" + appendopts.text + "</a>")
      //    .attr('href', this.opts.link_to.replace(/__id__/,page_id));
      }
        lnk.find('a').attr('href', this.opts.link_to.replace(/__id__/,page_id));
        //if (page_id == current_page) {
        //    lnk.attr('class', 'active');
        //}

      if(appendopts.classes){ lnk.addClass(appendopts.classes); }
      lnk.find('a').data('page_id', page_id);
      return lnk;
    },
    // Generate a range of numeric links 
    appendRange:function(container, current_page, start, end, opts) {
      var i;
      for(i=start; i<end; i++) {
        this.createLink(i, current_page, opts).appendTo(container);
      }
    },
    getLinks:function(current_page, eventHandler) {
      var begin, end,
        interval = this.pc.getInterval(current_page),
        np = this.pc.numPages(),
        // fragment = $("<div class='wp-inner'></div>");
        fragment = $("<ul/>");

      // Generate "Previous"-Link
      if(this.opts.prev_text && (current_page > 0 || this.opts.prev_show_always)){
        fragment.append(this.createLink(current_page-1, current_page, {text:this.opts.prev_text, classes:"prev"}));
      }
      // Generate starting points
      if (interval.start > 0 && this.opts.num_edge_entries > 0)
      {
        end = Math.min(this.opts.num_edge_entries, interval.start);
        this.appendRange(fragment, current_page, 0, end, {classes:'sp'});
        if(this.opts.num_edge_entries < interval.start && this.opts.ellipse_text)
        {
          $("<li><span>"+this.opts.ellipse_text+"</span></li>").appendTo(fragment);
        }
      }
      // Generate interval links
      this.appendRange(fragment, current_page, interval.start, interval.end);
      // Generate ending points
      if (interval.end < np && this.opts.num_edge_entries > 0)
      {
        if(np-this.opts.num_edge_entries > interval.end && this.opts.ellipse_text)
        {
          $("<li><span>"+this.opts.ellipse_text+"</span></li>").appendTo(fragment);
        }
        begin = Math.max(np-this.opts.num_edge_entries, interval.end);
        this.appendRange(fragment, current_page, begin, np, {classes:'ep'});
        
      }
      // Generate "Next"-Link
      if(this.opts.next_text && (current_page < np-1 || this.opts.next_show_always)){
        fragment.append(this.createLink(current_page+1, current_page, {text:this.opts.next_text, classes:"next"}));
      }
      $('a', fragment).click(eventHandler);
      return fragment;
    }
  });
  
  // Extend jQuery
  $.fn.pagination = function(maxentries, opts){
    
    // Initialize options with default values
    opts = $.extend({
      items_per_page:10,
      num_display_entries:11,
      current_page:0,
      num_edge_entries:0,
      link_to:"#",
      prev_text:"Prev",
      next_text:"Next",
      ellipse_text:"...",
      prev_show_always:true,
      next_show_always:true,
      renderer:"defaultRenderer",
      show_if_single_page:false,
      load_first_page:true,
      pre_name:'',
      callback:function(){return false;}
    },opts||{});
    
    var containers = this,
      renderer, links, current_page;
    
    /**
     * This is the event handling function for the pagination links. 
     * @param {int} page_id The new page number
     */
    function paginationClickHandler(evt){
      var links, 
        new_current_page = $(evt.target).data('page_id'),
        continuePropagation = selectPage(new_current_page);
      if (!continuePropagation) {
        evt.stopPropagation();
      }
      return continuePropagation;
    }
    
    /**
     * This is a utility function for the internal event handlers. 
     * It sets the new current page on the pagination container objects, 
     * generates a new HTMl fragment for the pagination links and calls
     * the callback function.
     */
    function selectPage(new_current_page) {
      // update the link display of a all containers
      containers.data('current_page', new_current_page);
      links = renderer.getLinks(new_current_page, paginationClickHandler);
      containers.empty();
      links.appendTo(containers);
      // call the callback and propagate the event if it does not return false
      var continuePropagation = opts.callback(new_current_page, containers, opts.pre_name);
      return continuePropagation;
    }
    
    // -----------------------------------
    // Initialize containers
    // -----------------------------------
    current_page = parseInt(opts.current_page);
    containers.data('current_page', current_page);
    // Create a sane value for maxentries and items_per_page
    maxentries = (!maxentries || maxentries < 0)?1:maxentries;
    opts.items_per_page = (!opts.items_per_page || opts.items_per_page < 0)?1:opts.items_per_page;
    
    if(!$.PaginationRenderers[opts.renderer])
    {
      throw new ReferenceError("Pagination renderer '" + opts.renderer + "' was not found in jQuery.PaginationRenderers object.");
    }
    renderer = new $.PaginationRenderers[opts.renderer](maxentries, opts);
    
    // Attach control events to the DOM elements
    var pc = new $.PaginationCalculator(maxentries, opts);
    var np = pc.numPages();
    containers.bind('setPage', {numPages:np}, function(evt, page_id) { 
        if(page_id >= 0 && page_id < evt.data.numPages) {
          selectPage(page_id); return false;
        }
    });
    containers.bind('prevPage', function(evt){
        var current_page = $(this).data('current_page');
        if (current_page > 0) {
          selectPage(current_page - 1);
        }
        return false;
    });
    containers.bind('nextPage', {numPages:np}, function(evt){
        var current_page = $(this).data('current_page');
        if(current_page < evt.data.numPages - 1) {
          selectPage(current_page + 1);
        }
        return false;
    });
    
    // When all initialisation is done, draw the links
    links = renderer.getLinks(current_page, paginationClickHandler);
    containers.empty();
    if(np > 1 || opts.show_if_single_page) {
      links.appendTo(containers);
    }
    // call callback function
    if(opts.load_first_page) {
      //commented for break away the location change;
      //opts.callback(current_page, containers, opts.pre_name);
    }
  } // End of $.fn.pagination block
  
})(jQuery);


/*!
 * jsUri
 * https://github.com/derek-watson/jsUri
 *
 * Copyright 2012, Derek Watson
 * Released under the MIT license.
 *
 * Includes parseUri regular expressions
 * http://blog.stevenlevithan.com/archives/parseuri
 * Copyright 2007, Steven Levithan
 * Released under the MIT license.
 *
 */

(function(global) {

    /**
     * Define forEach for older js environments
     * @see https://developer.mozilla.org/en-US/docs/JavaScript/Reference/Global_Objects/Array/forEach#Compatibility
     */
    if (!Array.prototype.forEach) {
        Array.prototype.forEach = function(fn, scope) {
            for (var i = 0, len = this.length; i < len; ++i) {
                fn.call(scope || this, this[i], i, this);
            }
        };
    }

    /**
     * unescape a query param value
     * @param  {string} s encoded value
     * @return {string}   decoded value
     */
    function decode(s) {
        s = decodeURIComponent(s);
        s = s.replace('+', ' ');
        return s;
    }

    /**
     * Breaks a uri string down into its individual parts
     * @param  {string} str uri
     * @return {object}     parts
     */
    function parseUri(str) {
        var parser = /^(?:(?![^:@]+:[^:@\/]*@)([^:\/?#.]+):)?(?:\/\/)?((?:(([^:@]*)(?::([^:@]*))?)?@)?([^:\/?#]*)(?::(\d*))?)(((\/(?:[^?#](?![^?#\/]*\.[^?#\/.]+(?:[?#]|$)))*\/?)?([^?#\/]*))(?:\?([^#]*))?(?:#(.*))?)/,
            parserKeys = ["source", "protocol", "authority", "userInfo", "user", "password", "host", "port", "relative", "path", "directory", "file", "query", "anchor"],
            m = parser.exec(str || ''),
            parts = {};

        parserKeys.forEach(function(key, i) {
            parts[key] = m[i] || '';
        });
        return parts;
    }

    /**
     * Breaks a query string down into an array of key/value pairs
     * @param  {string} str query
     * @return {array}      array of arrays (key/value pairs)
     */
    function parseQuery(str) {
        var i, ps, p, kvp, k, v,
            pairs = [];

        if (typeof(str) === 'undefined' || str === null || str === '') {
            return pairs;
        }

        if (str.indexOf('?') === 0) {
            str = str.substring(1);
        }

        ps = str.toString().split(/[&;]/);

        for (i = 0; i < ps.length; i++) {
            p = ps[i];
            kvp = p.split('=');
            k = kvp[0];
            v = p.indexOf('=') === -1 ? null : (kvp[1] === null ? '' : kvp[1]);
            pairs.push([k, v]);
        }
        return pairs;
    }

    /**
     * Creates a new Uri object
     * @constructor
     * @param {string} str
     */
    function Uri(str) {
        this.uriParts = parseUri(str);
        this.queryPairs = parseQuery(this.uriParts.query);
        this.hasAuthorityPrefixUserPref = null;
    }

    /**
     * Define getter/setter methods
     */
    ['protocol', 'userInfo', 'host', 'port', 'path', 'anchor'].forEach(function(key) {
        Uri.prototype[key] = function(val) {
            if (typeof val !== 'undefined') {
                this.uriParts[key] = val;
            }
            return this.uriParts[key];
        };
    });

    /**
     * if there is no protocol, the leading // can be enabled or disabled
     * @param  {Boolean}  val
     * @return {Boolean}
     */
    Uri.prototype.hasAuthorityPrefix = function(val) {
        if (typeof val !== 'undefined') {
            this.hasAuthorityPrefixUserPref = val;
        }

        if (this.hasAuthorityPrefixUserPref === null) {
            return (this.uriParts.source.indexOf('//') !== -1);
        } else {
            return this.hasAuthorityPrefixUserPref;
        }
    };

    /**
     * Serializes the internal state of the query pairs
     * @param  {string} [val]   set a new query string
     * @return {string}         query string
     */
    Uri.prototype.query = function(val) {
        var s = '',
            i, param;

        if (typeof val !== 'undefined') {
            this.queryPairs = parseQuery(val);
        }

        for (i = 0; i < this.queryPairs.length; i++) {
            param = this.queryPairs[i];
            if (s.length > 0) {
                s += '&';
            }
            if (param[1] === null) {
                s += param[0];
            } else {
                s += param.join('=');
                //s += param[0];
                //s += '=';
                //if (param[1]) {
                //    s += encodeURIComponent(param[1]);
                //}
            }
        }
        return s.length > 0 ? '?' + s : s;
    };

    /**
     * returns the first query param value found for the key
     * @param  {string} key query key
     * @return {string}     first value found for key
     */
    Uri.prototype.getQueryParamValue = function (key) {
        var param, i;
        for (i = 0; i < this.queryPairs.length; i++) {
            param = this.queryPairs[i];
            if (decode(key) === decode(param[0])) {
                return param[1];
            }
        }
    };

    /**
     * returns an array of query param values for the key
     * @param  {string} key query key
     * @return {array}      array of values
     */
    Uri.prototype.getQueryParamValues = function (key) {
        var arr = [],
            i, param;
        for (i = 0; i < this.queryPairs.length; i++) {
            param = this.queryPairs[i];
            if (decode(key) === decode(param[0])) {
                arr.push(param[1]);
            }
        }
        return arr;
    };

    /**
     * removes query parameters
     * @param  {string} key     remove values for key
     * @param  {val}    [val]   remove a specific value, otherwise removes all
     * @return {Uri}            returns self for fluent chaining
     */
    Uri.prototype.deleteQueryParam = function (key, val) {
        var arr = [],
            i, param, keyMatchesFilter, valMatchesFilter;

        for (i = 0; i < this.queryPairs.length; i++) {

            param = this.queryPairs[i];
            keyMatchesFilter = decode(param[0]) === decode(key);
            valMatchesFilter = decode(param[1]) === decode(val);

            if ((arguments.length === 1 && !keyMatchesFilter) || (arguments.length === 2 && !keyMatchesFilter && !valMatchesFilter)) {
                arr.push(param);
            }
        }

        this.queryPairs = arr;

        return this;
    };

    /**
     * adds a query parameter
     * @param  {string}  key        add values for key
     * @param  {string}  val        value to add
     * @param  {integer} [index]    specific index to add the value at
     * @return {Uri}                returns self for fluent chaining
     */
    Uri.prototype.addQueryParam = function (key, val, index) {
        if (arguments.length === 3 && index !== -1) {
            index = Math.min(index, this.queryPairs.length);
            this.queryPairs.splice(index, 0, [key, val]);
        } else if (arguments.length > 0) {
            this.queryPairs.push([key, val]);
        }
        return this;
    };

    /**
     * replaces query param values
     * @param  {string} key         key to replace value for
     * @param  {string} newVal      new value
     * @param  {string} [oldVal]    replace only one specific value (otherwise replaces all)
     * @return {Uri}                returns self for fluent chaining
     */
    Uri.prototype.replaceQueryParam = function (key, newVal, oldVal) {

        var index = -1,
            i, param;

        if (arguments.length === 3) {
            for (i = 0; i < this.queryPairs.length; i++) {
                param = this.queryPairs[i];
                if (decode(param[0]) === decode(key) && decodeURIComponent(param[1]) === decode(oldVal)) {
                    index = i;
                    break;
                }
            }
            this.deleteQueryParam(key, oldVal).addQueryParam(key, newVal, index);
        } else {
            for (i = 0; i < this.queryPairs.length; i++) {
                param = this.queryPairs[i];
                if (decode(param[0]) === decode(key)) {
                    index = i;
                    break;
                }
            }
            this.deleteQueryParam(key);
            this.addQueryParam(key, newVal, index);
        }
        return this;
    };

    /**
     * Define fluent setter methods (setProtocol, setHasAuthorityPrefix, etc)
     */
    ['protocol', 'hasAuthorityPrefix', 'userInfo', 'host', 'port', 'path', 'query', 'anchor'].forEach(function(key) {
        var method = 'set' + key.charAt(0).toUpperCase() + key.slice(1);
        Uri.prototype[method] = function(val) {
            this[key](val);
            return this;
        };
    });

    /**
     * Scheme name, colon and doubleslash, as required
     * @return {string} http:// or possibly just //
     */
    Uri.prototype.scheme = function() {

        var s = '';

        if (this.protocol()) {
            s += this.protocol();
            if (this.protocol().indexOf(':') !== this.protocol().length - 1) {
                s += ':';
            }
            s += '//';
        } else {
            if (this.hasAuthorityPrefix() && this.host()) {
                s += '//';
            }
        }

        return s;
    };

    /**
     * Same as Mozilla nsIURI.prePath
     * @return {string} scheme://user:password@host:port
     * @see  https://developer.mozilla.org/en/nsIURI
     */
    Uri.prototype.origin = function() {

        var s = this.scheme();

        if (this.userInfo() && this.host()) {
            s += this.userInfo();
            if (this.userInfo().indexOf('@') !== this.userInfo().length - 1) {
                s += '@';
            }
        }

        if (this.host()) {
            s += this.host();
            if (this.port()) {
                s += ':' + this.port();
            }
        }

        return s;
    };

    /**
     * Serializes the internal state of the Uri object
     * @return {string}
     */
    Uri.prototype.toString = function() {

        var s = this.origin();

        if (this.path()) {
            s += this.path();
        } else {
            if (this.host() && (this.query().toString() || this.anchor())) {
                s += '/';
            }
        }
        if (this.query().toString()) {
            if (this.query().toString().indexOf('?') !== 0) {
                s += '?';
            }
            s += this.query().toString();
        }

        if (this.anchor()) {
            if (this.anchor().indexOf('#') !== 0) {
                s += '#';
            }
            s += this.anchor();
        }

        return s;
    };

    /**
     * Clone a Uri object
     * @return {Uri} duplicate copy of the Uri
     */
    Uri.prototype.clone = function() {
        return new Uri(this.toString());
    };

    /**
     * export via CommonJS, otherwise leak a global
     */
    if (typeof module === 'undefined') {
        global.Uri = Uri;
    } else {
        module.exports = Uri;
    }
}(this));