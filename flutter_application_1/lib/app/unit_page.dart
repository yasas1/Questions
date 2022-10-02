import 'package:flutter/material.dart';

class UnitPage extends StatefulWidget {
  final String username;
  final String unitName;

  const UnitPage({Key? key, required this.username, required this.unitName})
      : super(key: key);

  @override
  State<UnitPage> createState() => _UnitPageState();
}

class _UnitPageState extends State<UnitPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Unit"),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Text("Welcome ${widget.username} for ${widget.unitName}"),
          ],
        ),
      ),
    );
  }
}
