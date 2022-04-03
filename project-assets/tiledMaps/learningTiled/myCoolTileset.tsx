<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.5" tiledversion="1.7.0" name="myCoolTileset" tilewidth="32" tileheight="32" spacing="1" margin="1" tilecount="100" columns="10">
 <image source="myCoolTileset.png" width="331" height="331"/>
 <tile id="3" probability="0.005"/>
 <tile id="4" probability="0.005"/>
 <tile id="5" probability="0.005"/>
 <tile id="6" probability="0.005"/>
 <tile id="13" probability="0.005">
  <properties>
   <property name="blueFlowerAnimation" value="heyyo"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="2.95652" y="19.4348">
    <polygon points="0,0 6.13043,-6.65217 7.08696,-6.65217 9.21739,-4.47826 14.087,-2.52174 16.0435,-0.217391 16.1739,4 17.3913,4.69565 15.3913,9.6087 4.30435,10.6957 1.73913,6.30435"/>
   </object>
  </objectgroup>
 </tile>
 <tile id="14" probability="0.005">
  <properties>
   <property name="blueFlowerAnimation" value="heyyo"/>
  </properties>
 </tile>
 <tile id="15" probability="0.005">
  <properties>
   <property name="blueFlowerAnimation" value="heyyo"/>
  </properties>
 </tile>
 <tile id="16" probability="0.005">
  <properties>
   <property name="blueFlowerAnimation" value="heyyo"/>
  </properties>
 </tile>
 <wangsets>
  <wangset name="DirtAndSand" type="corner" tile="-1">
   <wangcolor name="Dirt" color="#ff0000" tile="-1" probability="1"/>
   <wangcolor name="Sand" color="#00ff00" tile="-1" probability="1"/>
   <wangtile tileid="0" wangid="0,1,0,2,0,1,0,1"/>
   <wangtile tileid="1" wangid="0,1,0,2,0,2,0,1"/>
   <wangtile tileid="2" wangid="0,1,0,1,0,2,0,1"/>
   <wangtile tileid="3" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="4" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="5" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="6" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="10" wangid="0,2,0,2,0,1,0,1"/>
   <wangtile tileid="11" wangid="0,2,0,2,0,2,0,2"/>
   <wangtile tileid="12" wangid="0,1,0,1,0,2,0,2"/>
   <wangtile tileid="13" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="14" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="15" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="16" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="20" wangid="0,2,0,1,0,1,0,1"/>
   <wangtile tileid="21" wangid="0,2,0,1,0,1,0,2"/>
   <wangtile tileid="22" wangid="0,1,0,1,0,1,0,2"/>
   <wangtile tileid="30" wangid="0,2,0,1,0,2,0,2"/>
   <wangtile tileid="31" wangid="0,2,0,2,0,1,0,2"/>
   <wangtile tileid="32" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="40" wangid="0,1,0,2,0,2,0,2"/>
   <wangtile tileid="41" wangid="0,2,0,2,0,2,0,1"/>
  </wangset>
 </wangsets>
</tileset>
