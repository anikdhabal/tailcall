package tailcall.gateway.remote.operations

import tailcall.gateway.remote.DynamicEval.TupleOperations
import tailcall.gateway.remote.Remote

trait TupleOps {
  implicit final class RemoteTupleOps[A, B](val self: Remote[(A, B)]) {
    def _1: Remote[A]                      = getIndex(0)
    def _2: Remote[B]                      = getIndex(1)
    def getIndex[A](index: Int): Remote[A] =
      Remote
        .unsafe
        .attempt(ctx =>
          TupleOperations(TupleOperations.GetIndex(self.compile(ctx), index))
        )
  }
}