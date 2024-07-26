milestone 1:
დავამატე დატაბეიზში ფაილები:
Database.java - ინტერფეისი, რა მეთოდები გვჭირდება

DatabaseInfo.java - იუზერი, პაროლი, სერვერი... შენს კომპზე MySql-თან დასაკავშირებლად

DbConnection.java - ამყარებს ქონექშენს ბაზასთან, აქვს შემდეგი მეთოდები:
    getConnection() / getDbManager() - self-explanatory
    runSqlFile(String filename) - უშვებს გადაცემულ ფაილს

FriendshipStatus.java - უბრალოდ გადანომრვა სტატუსის, მერე უფრო დაგვჭირდება

UserDbManager.java - აიმპემენტებს Database.java-ს მეთოდები მანდ შეგიძლიათ ნახოთ

ასევე ContextListener-ში გავატანე UserDbManager ბაზასთან საურთიერთოდ.

შევქმენი 2 მთავარი table: users and friends, ასევე მათი კლონები სატესტოდ:
users.sql
friends.sql
testUsers.sql

milestone 2:
დავამატე პროფილის jsp სადაც გამოტანილია იუზერის სახელი, გვარი, სურათი, ბიო და მეგობრების სია

ასევე შევქმენი editProfile.jsp საიდანაც იუზერს ინფორმაციის შეცვლა შეეძლება. (რაც, ცხადია ბაზაშიც აისახება)


milestone 3:
დავამატე multiPage და singlPage ქვიზის შევსება

milestone 4:
დავამატე singlePageQuizResults სადაც შევსებული ქვიზის ფიდბექი გამოდის, რომელ კითხვას გაეცი სწორად პასუხი და რა ქულა აიღე.

milestone 4:
დავამატე ქვიზის ისტორიის შენახვა და ასევე ქვიზის შევსებას დავამატე css + immediate Correction ფუნქცია, ეგრევე რომ გამოუტანოს ქულა პასუხი

milestone 5:
მეგობრების გაგზავნის ლოგიკა და ნოუთები

milestone 6:
ექსთენშენი: Practice Mode + ტაიმერი

milestone 7: 
პროფილის edit Profile ის დიზაინი


milestone 8:
unit tests for UserDbManager




