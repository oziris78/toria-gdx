varying vec4 v_color;
varying vec2 v_texCoord0;

uniform sampler2D u_sampler2D;

void main(){
//    gl_FragColor = texture2D(u_sampler2D, v_texCoord0) * v_color;
    gl_FragColor = vec4(1.0f,0.0f,0.0f,1.0f);
}

/*
 A uniform stays the same for every single vertex.
 Attributes on the other hand can vary from vertex to vertex.
 Obviously this can have performance implications, so if it makes sense,
 prefer using a uniform.
 A varying value on the other hand can be thought of as the return value,
 these values will be passed on down the rendering pipeline
 ( meaning the fragment shader has access to them ).
 As you can see from the use of gl_Position, OpenGL also has some built in values.
 For vertex shaders there are gl_Position and gl_PointSize.
 Think of these as uniform variables provided by OpenGL itself.
 gl_Position is ultimately the position of your vertex in the world.
*/