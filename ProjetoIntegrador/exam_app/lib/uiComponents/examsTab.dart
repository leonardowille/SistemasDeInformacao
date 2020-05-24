import 'package:examapp/pages/examPage.dart';
import 'package:examapp/uiComponents/chart.dart';
import 'package:examapp/uiComponents/examList.dart';
import 'package:flutter/material.dart';

class ExamTab extends StatelessWidget {
  _goToExam(context) {
    Navigator.push(
        context, MaterialPageRoute(builder: (context) => ExamPage.startPage()));
  }

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        Column(
          children: <Widget>[
            Expanded(
              flex: 1,
              child: Container(
                color: Colors.red,
                child: Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Chart.withSampleData(),
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(12.0),
              child: Text(
                "Listagem de Exames",
                style: TextStyle(fontSize: 16),
              ),
            ),
            Expanded(
              flex: 2,
              child: ExamList(),
            ),
          ],
        ),
        Padding(
          padding: const EdgeInsets.all(16.0),
          child: Align(
            alignment: Alignment.bottomCenter,
            child: InkWell(
              child: Container(
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(10),
                    color: Colors.yellow,
                  ),
                  child: Padding(
                      padding: const EdgeInsets.all(6.0),
                      child: Text(
                        "Adicionar Novo Exame",
                        style: TextStyle(
                            fontSize: 16, fontWeight: FontWeight.w500),
                      ))),
              onTap: () => _goToExam(context),
            ),
          ),
        ),
      ],
    );
  }
}
