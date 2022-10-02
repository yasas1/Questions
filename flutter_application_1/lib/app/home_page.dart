import 'package:flutter/material.dart';
import 'package:flutter_application_1/app/login_page/splash_page.dart';
import 'package:flutter_application_1/app/unit_page.dart';
import 'package:scroll_snap_list/scroll_snap_list.dart';

class HomePage extends StatefulWidget {
  final String username;

  const HomePage({Key? key, required this.username}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {

  List<String> data = ["Unit One", "Unit Two", "Unit Three"];
  int _focusedIndex = 0;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Home"),
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          const SizedBox(height: 10,),
          Text("Welcome ${widget.username}",
            style: const TextStyle(color: Colors.blueGrey),),
          const SizedBox(height: 20,),
          SizedBox(
            height: 250,
            child: ScrollSnapList(
              itemBuilder: _buildListItem,
              itemCount: data.length,
              itemSize: 150,
              onItemFocus: _onItemFocus,
              dynamicItemSize: true,
              focusOnItemTap: true,
            ),
          ),
          const SizedBox(height: 20,),
          ElevatedButton(
            onPressed: () {
              Navigator.pushReplacement(
                  context,
                  MaterialPageRoute(
                      builder: (context) => const SplashPage()));
            },
            child: const Text("Log out"),
          ),
        ],
      ),
    );
  }

  void _onItemFocus(int index) {
    setState(() {
      _focusedIndex = index;
    });
  }

  Widget _buildListItem(BuildContext context, int index) {
    String unitName = data[index];
    return SizedBox(
      width: 150,
      height: 300,
      child: Card(
        elevation: 12,
        child: ClipRRect(
          borderRadius: const BorderRadius.all(Radius.circular(10)),
          child: Column(
            children: [
              Image.asset(
                "assets/images/flutter.png",
                fit: BoxFit.cover,
                width: 150,
                height: 180,),
              const SizedBox(height: 10,),
              ElevatedButton(
                onPressed: () {
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => UnitPage(username:widget.username, unitName: unitName,)));
                },
                  child: Text(
                    unitName,
                    style: const TextStyle(fontSize: 15, color: Colors.black),
                  ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
