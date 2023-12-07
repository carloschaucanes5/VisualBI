var labelType, useGradients, nativeTextSupport, animate;

(function() {
  var ua = navigator.userAgent,
      iStuff = ua.match(/iPhone/i) || ua.match(/iPad/i),
      typeOfCanvas = typeof HTMLCanvasElement,
      nativeCanvasSupport = (typeOfCanvas == 'object' || typeOfCanvas == 'function'),
      textSupport = nativeCanvasSupport 
        && (typeof document.createElement('canvas').getContext('2d').fillText == 'function');
  //I'm setting this based on the fact that ExCanvas provides text support for IE
  //and that as of today iPhone/iPad current text support is lame
  labelType = (!nativeCanvasSupport || (textSupport && !iStuff))? 'Native' : 'HTML';
  nativeTextSupport = labelType == 'Native';
  useGradients = nativeCanvasSupport;
  animate = !(iStuff || !nativeCanvasSupport);
})();

var Log = {
  elem: false,
  write: function(text){
    if (!this.elem) 
      this.elem = document.getElementById('log');
    this.elem.innerHTML = text;
    this.elem.style.left = (this.elem.offsetWidth / 2) + 'px';
  }
};


var icicle;

function init(){
  //left panel controls
  controls();

var json= {"name":"\nN0 ->K","children":[{"name":"\nN1 ->K","children":[{"name":"\nN2 ->drugY (56.0)","children":null,"id":"<= 0.037124"},{"name":"\nN3 ->Na","children":[{"name":"\nN4 ->BP","children":[{"name":"\nN5 ->Na","children":[{"name":"\nN6 ->drugA (6.0)","children":null,"id":"<= 0.656371"},{"name":"\nN7 ->drugY (2.0\/1.0)","children":null,"id":"> 0.656371"}],"id":"= HIGH"},{"name":"\nN8 ->Sex","children":[{"name":"\nN9 ->drugC (3.0)","children":null,"id":"= F"},{"name":"\nN10 ->drugX (4.0\/1.0)","children":null,"id":"= M"}],"id":"= LOW"},{"name":"\nN11 ->drugX (11.0\/1.0)","children":null,"id":"= NORMAL"}],"id":"<= 0.685143"},{"name":"\nN12 ->drugY (33.0\/2.0)","children":null,"id":"> 0.685143"}],"id":"> 0.037124"}],"id":"<= 0.055221"},{"name":"\nN13 ->BP","children":[{"name":"\nN14 ->Age","children":[{"name":"\nN15 ->drugA (17.0)","children":null,"id":"<= 50"},{"name":"\nN16 ->drugB (15.0)","children":null,"id":"> 50"}],"id":"= HIGH"},{"name":"\nN17 ->Cholesterol","children":[{"name":"\nN18 ->drugC (14.0\/1.0)","children":null,"id":"= HIGH"},{"name":"\nN19 ->drugX (13.0)","children":null,"id":"= NORMAL"}],"id":"= LOW"},{"name":"\nN20 ->drugX (26.0)","children":null,"id":"= NORMAL"}],"id":"> 0.055221"}],"id":""}

  // init data
  /*var json = {
    "id": "=Si, para contribuir a pagar su matrícula y/o los gastos del hogar (155.0/53.0..--",
    "name": "\nN0 ->Nodo0jkjkljlkjlkjlkjlkjlkjlkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjkkkkkkkkkkkkkzzzzzzzzz yyykkkkkkkkkkkkklllllllllllllllllllllllllllljhgggfgfgfgfsesdffffffffff",
    "data": {"$color": "#001eff"},
    "children": 
     [
        {
          "id": "=1",
          "name": "\nN1 ->Nodo1",
          "data": { "$color": "#9554ff"}
        },
        {
          "id": "=2",
          "name": "\nN2 ->Nodo2",
          "data": {"$color": "#9599ff"}
        }
    ]

  };*/
  // end
  // init Icicle
  icicle = new $jit.Icicle({
    // id of the visualization container
    injectInto: 'infovis',
    // whether to add transition animations
    animate: animate,
    // nodes offset
    offset: 1,
    // whether to add cushion type nodes
    cushion: false,
    //show only three levels at a time
    constrained: true,
    levelsToShow: 3,
    // enable tips
    Tips: {
      enable: true,
      type: 'Native',
      // add positioning offsets
      offsetX: 20,
      offsetY: 20,
      // implement the onShow method to
      // add content to the tooltip when a node
      // is hovered
      onShow: function(tip, node){
        // count children
        var count = 0;
        node.eachSubnode(function(){
          count++;
        });
        // add tooltip info
        tip.innerHTML = "<div class=\"tip-title\"><b>Name:</b> " + node.id
            + "</div><div class=\"tip-text\">" + count + " children</div>";
      }
    },
    // Add events to nodes
    Events: {
      enable: true,
      onMouseEnter: function(node) {
        //add border and replot node
        node.setData('border', '#33dddd');
        icicle.fx.plotNode(node, icicle.canvas);
        icicle.labels.plotLabel(icicle.canvas, node, icicle.controller);
      },
      onMouseLeave: function(node) {
        node.removeData('border');
        icicle.fx.plot();
      },
      onClick: function(node){
        if (node) {
          //hide tips and selections
          icicle.tips.hide();
          if(icicle.events.hovered)
            this.onMouseLeave(icicle.events.hovered);
          //perform the enter animation
          icicle.enter(node);
          
        }
      },
      onRightClick: function(){
        //hide tips and selections
        icicle.tips.hide();
        if(icicle.events.hovered)
          this.onMouseLeave(icicle.events.hovered);
        //perform the out animation
        icicle.out();
      }
    },
    // Add canvas label styling
    Label: {
      type: labelType // "Native" or "HTML"
    },
    // Add the name of the node in the corresponding label
    // This method is called once, on label creation and only for DOM and not
    // Native labels.
    onCreateLabel: function(domElement, node){
      domElement.innerHTML = node.name;
      var style = domElement.style;
      style.fontSize = '0.9em';
      style.display = '';
      style.cursor = 'pointer';
      style.color = 'green';
      style.overflow = 'hidden';
    },
    // Change some label dom properties.
    // This method is called each time a label is plotted.
    onPlaceLabel: function(domElement, node){
      var style = domElement.style,
          width = node.getData('width'),
          height = node.getData('height');
      if(width < 7 || height < 7) {
        style.display = 'none';
      } else {
        style.color = 'red';
        style.display = '';
        style.width = width + 'px';
        style.height = height + 'px';
      }
    }
  });
  // load data
  icicle.loadJSON(json);
  // compute positions and plot
  icicle.refresh();
  //end
}

//init controls
function controls() {
  var jit = $jit;
  var gotoparent = jit.id('update');
  jit.util.addEvent(gotoparent, 'click', function() {
    icicle.out();
  });
  var select = jit.id('s-orientation');
  jit.util.addEvent(select, 'change', function () {
    icicle.layout.orientation = select[select.selectedIndex].value;
    icicle.refresh();
  });
  var levelsToShowSelect = jit.id('i-levels-to-show');
  jit.util.addEvent(levelsToShowSelect, 'change', function () {
    var index = levelsToShowSelect.selectedIndex;
    if(index == 0) {
      icicle.config.constrained = false;
    } else {
      icicle.config.constrained = true;
      icicle.config.levelsToShow = index;
    }
    icicle.refresh();
  });
}
//end
