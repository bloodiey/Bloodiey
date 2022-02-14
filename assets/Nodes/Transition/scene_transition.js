cc.Class({
    extends: cc.Component,

    properties: {
        // foo: {
        //    default: null,      // The default value will be used only when the component attaching
        //                           to a node for the first time
        //    url: cc.Texture2D,  // optional, default is typeof default
        //    serializable: true, // optional, default is true
        //    visible: true,      // optional, default is true
        //    displayName: 'Foo', // optional
        //    readonly: false,    // optional, default is false
        // },
        // ...
        Escena: {
          default: String,
          displayName: 'Scene',
          visible: true,
          readonly: false,
        },
    },

    // use this for initialization
    onLoad: function () {
        cc.game.addPersistRootNode(this.node);
    },
    Load_Next_Scene: function (){

        cc.tween(this.node)
          .to(1, {position: cc.v2(640,360) },{easing: 'cubicInOut'})
          .call(() => { this.Load_Scene(); })
          .to(1, {position: cc.v2(-640,360) },{easing: 'cubicInOut'})
          .start();

    },

    Load_Scene: function (){
        cc.director.loadScene("TestScene");
    }

    // called every frame, uncomment this function to activate update callback
    // update: function (dt) {

    // },
});
