import 'package:examapp/models/Exam.dart';
import 'package:examapp/pages/examPage.dart';
import 'package:examapp/services/examService.dart';
import 'package:examapp/uiComponents/examListItem.dart';
import 'package:flutter/material.dart';

class ExamTab extends StatefulWidget {
  @override
  _ExamTabState createState() => _ExamTabState();
}

class _ExamTabState extends State<ExamTab> {
  ExamService examService = ExamService();

  List<Exam> exams = [];

  _goToExam(context) {
    Navigator.push(
        context, MaterialPageRoute(builder: (context) => ExamPage()));
  }

  _getMyExams() {
    examService.getMyExams((response) {
      response.data.forEach((examData) {
        setState(() {
          this.exams.add(Exam.fromJson(examData));
        });
      });
    }, () {});
  }

  Widget _listItem() {
    if (this.exams == null || this.exams.length == 0) {
      _getMyExams();
      return Text("Carregando...");
    }
    return Container(
      height: 250,
      width: double.infinity,
      color: Colors.green,
      child: ListView.builder(
        itemCount: exams.length,
        itemBuilder: (context, index) {
          return ExamListItem(exam: exams[index]);
        },
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Expanded(
          flex: 1,
          child: Container(
            color: Colors.greenAccent,
            width: double.infinity,
            child: Text("Gráfico de exames"),
          ),
        ),
        Container(
          height: 10,
          color: Colors.amber,
        ),
        Container(
          color: Colors.blueAccent,
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
            children: <Widget>[
              Text("Listagem de exames"),
              InkWell(
                child: Text("+"),
                onTap: () => _goToExam(context),
              )
            ],
          ),
        ),
        Expanded(flex: 2, child: _listItem()),
      ],
    );
  }
}
