# ProtoFast
During the preparations for the [libGDXJam](http://itch.io/jam/libgdxjam), the team composed of [Fahien](https://twitter.com/Fahien) and [pasto](https://sketchfab.com/pasto), so called **FaSTeam**, needed to test the effectiveness of its work by creating a prototype application which involves the use of [libGDX](https://libgdx.badlogicgames.com) and [Blender](https://www.blender.org).

[ProtoFast](http://www.fahien.me/protofast) is the codename of this project. The final product is a simple application which shows a three-dimensional model through a camera movable via mouse or touch screen.

Practically, ProtoFast creates a list of models contained in the local <code>models/</code> folder. If there are no models in the local folder, it searches in the internal <code>models/</code> folder. The *showcase screen* loads the first model from the list and renders it. You can load the next or previous model through the arrows.

## Screenshots
The models shown in the screenshots are made by pasto.

![Earth](screenshots/sf2.png?raw=true "Earth")

![Spaceship](screenshots/sf1.png?raw=true "Spaceship")

## License
ProtoFast is licensed under the [Apache 2 License](http://www.apache.org/licenses/LICENSE-2.0.html).