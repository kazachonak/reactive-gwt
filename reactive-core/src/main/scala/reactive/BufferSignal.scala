package reactive

/**
 * Convenience class for a Var that is a SeqSignal.
 */
class SeqVar[A](init: A*) extends SimpleVar(DeltaSeq.fromSeq(init)) with SeqSignal[A]

/**
 * This SeqSignal contains a Buffer which you can modify directly,
 * causing deltas to be fired. You can also replace the buffer contents directly,
 * causing a diff to be calculated and the resulting deltas
 * to be fired.
 */
class BufferSignal[T] extends SeqSignal[T] {
  val underlying = new ObservableBuffer[T]
  val change = new EventSource[DeltaSeq[T]]
  private val dl: SeqDelta[T, T] => Unit = { d =>
    change fire now
  }
  val now = new DeltaSeq[T] {
    def signal(implicit observing: Observing) = BufferSignal.this
    val underlying = BufferSignal.this.underlying
    // We pass new Observing{}, because we don't ever need to manually delete event listeners
    val fd = underlying.messages.hold(DeltaSeq.startDelta(underlying))(new Observing{})
    def fromDelta = fd.now
  }
  // We pass new Observing{}, because we don't ever need to manually delete event listeners
  underlying.messages.addListener(dl, new Observing{})

  /**
   * Override this to customize the comparator used
   * by the diff algorithm when updating the
   * value with a whole Seq.
   */
  def comparator: (T, T) => Boolean = { _ == _ }
  def value = underlying

  /**
   * Set the contents from another Seq. Does not set it directly;
   * rather calculates the diff and applies it.
   * Usage: bufferSignal.value = newContentsSeq
   */
  def value_=(v: Seq[T]) {
    val diff = Batch(LCS.lcsdiff(now, v, comparator): _*)
    underlying applyDelta diff
  }
  /**
   * Usage: bufferSignal ()= newContentsSeq
   */
  final def update(v: Seq[T]) = value = v

  override def toString = "BufferSignal("+now+")"
}
object BufferSignal {
  def apply[T](init: T*): BufferSignal[T] = new BufferSignal[T] {
    value = init
  }
}

