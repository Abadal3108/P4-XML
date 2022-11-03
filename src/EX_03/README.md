### P4 XML

## EX_03
URL url = new URL(loc);
String path = url.getPath().replace('/', '_');
System.out.println(path);

                    try (PrintStream docu = new PrintStream("C:\Users\Alex\IdeaProjects\P4\xmls\" + path + ".txt")) {


                    }

creamos una url hacemos el replace y creamos un file con el nombre del path con la extension .txt.

      try {
                                Files.write(Paths.get("C:\Users\Alex\IdeaProjects\P4\xmls\" + path + ".txt"), loc_2.getBytes(), StandardOpenOption.APPEND);
                            } catch (IOException e) {
                                //exception handling left as an exercise for the reader
                            }

entramos en el fichero y hacemos un append para que no se sobrescriba.