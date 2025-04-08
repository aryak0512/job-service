db = db.getSiblingDB("job"); // Creates or switches to "mydb"

db.job.insertMany([
    {company: "Microsoft", skills: ["Java", "Spring"], salary: 46648},
    {company: "Apple", skills: ["Java"], salary: 46456}
]);