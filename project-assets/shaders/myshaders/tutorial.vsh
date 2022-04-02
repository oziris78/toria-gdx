attribute vec4 a_color; // final, you dont assign anything to them
attribute vec2 a_texCoord0; // vec2 for x,y
attribute vec3 a_position; // vec3 for x,y,z

// uniforms are our variables
// varying vars are the ones that are passed along
// attributes are final vars
uniform mat4 u_projTrans;

varying vec4 v_color;
varying vec2 v_texCoord0;

void main(){

    v_color = a_color;
    v_texCoord0 = a_texCoord0;
    gl_Position = u_projTrans * vec4(a_position, 1.0);  // built-in var
}