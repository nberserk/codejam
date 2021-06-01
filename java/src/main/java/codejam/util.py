
class util:
    @staticmethod
    def output(line):
        try:
            print(line)
            sys.stdout.flush()
        except:
            # If we let stdout be closed by the end of the program, then an unraisable
            # broken pipe exception will happen, and we won't be able to finish
            # normally.
            try:
                sys.stdout.close()
            except:
                pass
