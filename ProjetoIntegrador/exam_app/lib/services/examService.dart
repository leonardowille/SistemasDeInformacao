import 'package:examapp/api/examApi.dart';
import 'package:examapp/models/Exam.dart';

class ExamService {
  final ExamApi _api = ExamApi();

  saveExam(Exam exam, Function onSuccess, Function onError) async {
    if (exam.id == null) {
      await _api.createExam(exam, onSuccess, onError);
    } else {
      await _api.updateExam(exam, onSuccess, onError);
    }
  }

  getMyExams(Function onSuccess, Function onError) async {
    await _api.getMyExams(onSuccess, onError);
  }

  removeExam(int id, Function onSuccess, Function onError) async {
    await _api.removeExam(id, onSuccess, onError);
  }
}
