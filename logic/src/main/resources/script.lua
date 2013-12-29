jframe = luajava.bindClass( "javax.swing.JFrame" )
	frame = luajava.newInstance( "javax.swing.JFrame", "Texts" );
	frame:setDefaultCloseOperation(jframe.EXIT_ON_CLOSE)
	frame:setSize(300,400)
	frame:setVisible(true)