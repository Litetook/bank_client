package main.java.com.pragmatic.dao;

//ПО ідеї, десь має бути схема csv файла, яка відноситься до його об'єкта. І бажано щоб параметри завжди співпадали, бо те що ти і читаєш,
// ти і записуєш, порядок має бути точно такий самий.





public interface Idao {
    public Boolean appendLine(String tableName);
}
