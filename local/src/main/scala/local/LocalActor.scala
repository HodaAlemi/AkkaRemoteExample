package local

import akka.actor._

object LocalActor extends App {

  implicit val system = ActorSystem("LocalSystem")
  val localActor = system.actorOf(Props[LocalActor], name = "LocalActor")  // the local actor
  localActor ! "START"                                                     // start the action

}

class LocalActor extends Actor {
  val host = readLine("What is remote IP address? ")

  val path = s"akka.tcp://RemoteSystem@${host}:8082/user/RemoteActor"

  // creating the remote actor
  val remote = context.actorSelection(path)

  var counter = 0

  def receive = {

    case "START" =>
      remote ! "Hello from the LocalActor"

    case msg: String =>
      println(s"LocalActor received message: '$msg'")
      if (counter < 5) {
        sender ! "Hello back to you"
        counter += 1
      }
  }
}
