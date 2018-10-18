attribute vec4 a_Position;
attribute vec4 a_TextureCoordinates;
varying vec2 v_TextureCoordinates;
uniform mat4 u_Matrix;

void main(){
  v_TextureCoordinates = a_TextureCoordinates;
  gl_position = a_Position;
}